package com.momomo.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by danielsauve on 2017-02-19.
 */

public interface SkillRepository extends CrudRepository<Skill, String>{
    Skill findByName(String name);
}
