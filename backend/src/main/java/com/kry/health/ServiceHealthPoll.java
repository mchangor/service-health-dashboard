package com.kry.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Configurable
public class ServiceHealthPoll implements Runnable {

    Logger logger = LoggerFactory.getLogger(ServiceHealthPoll.class);

    @Autowired
    private ServiceRepository serviceRepository;

    @Scheduled(fixedRate = 30000)
    public void run() {
        logger.info("Starting to poll health of the services.");

        RestTemplate restTemplate = new RestTemplate();

        Iterable<ServiceObj> services = serviceRepository.findAll();

        for (ServiceObj service : services) {
            try {
                ResponseEntity<String> result = restTemplate.getForEntity(service.getUrl(), String.class);
                String status = result.getBody();
                logger.info("Status received for service {}: {}", service.getName(), status);

                // Set status to UNKNOWN if the status is not OK or FAIL
                if (status.equals("OK") | status.equals("FAIL")) {
                    service.setStatus(status);
                } else {
                    service.setStatus("UNKNOWN");
                }

                // Save status to repository
                serviceRepository.save(service);
                logger.info("Service status saved.");
            } catch (Exception e) {
                service.setStatus("UNKNOWN");
                serviceRepository.save(service);
                logger.info("There was an error in fetching status of {} with url {}. Status set to default: UNKNOWN",
                        service.getName(), service.getUrl());
                logger.error(String.valueOf(e));
            }
        }

        logger.info("Polling of health of the services is done.");
    }
}