package com.kry.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(path="/")
// Added frontend URL here to enable CORS
@CrossOrigin(origins = "http://localhost:3000")
public class RestController {

	private static Logger logger = LoggerFactory.getLogger(RestController.class);

	@Autowired
	private ServiceRepository serviceRepository;

	@PostMapping(path="/add")
	public @ResponseBody String add (@RequestParam String name, 
        @RequestParam String url) {
		ServiceObj service = new ServiceObj();
		service.setName(name);
		service.setUrl(url);
        service.setStatus("UNKNOWN"); // default status
		service.setCreationTime();
		logger.info("Service received: {}", name);
		serviceRepository.save(service);
		logger.info("Service saved: {}", name);
		return "Service Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<ServiceObj> getAll() {
		logger.info("Listing all the services");
		return serviceRepository.findAll();
	}

	@GetMapping(path="/service")
	public @ResponseBody ServiceObj getServiceById(@RequestParam Integer id) {
		logger.info("Fetching details by Id");
		return serviceRepository.findById(id).get();
	}
}
