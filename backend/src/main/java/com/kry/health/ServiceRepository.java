package com.kry.health;

import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<ServiceObj, Integer> {
}