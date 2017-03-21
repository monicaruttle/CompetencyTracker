package com.momomo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by danielsauve on 2017-02-19.
 */
public interface LearningMaterialRepository extends CrudRepository<LearningMaterial, String>{
    LearningMaterial findByName(String name);
}