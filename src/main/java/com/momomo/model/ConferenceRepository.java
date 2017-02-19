package com.momomo.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Monica on 2017-02-19.
 */
public interface ConferenceRepository extends CrudRepository<Conference, String> {
    Conference findByName(String name);
}
