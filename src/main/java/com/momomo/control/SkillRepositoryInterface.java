package com.momomo.control;

import com.momomo.model.SkillRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Charberg on 3/5/2017.
 */
@Getter
@Setter
@Component
public class SkillRepositoryInterface {

    @Autowired
    private SkillRepository repo;

    private SkillRepositoryInterface() {

    }

    //TODO Implement methods interacting with repo here


}
