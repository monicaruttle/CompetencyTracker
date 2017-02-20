package com.momomo.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by danielsauve on 2017-02-19.
 */
public interface LearningMaterialRepository extends CrudRepository<LearningMaterial, String>{
    LearningMaterial findByName(String name);
}