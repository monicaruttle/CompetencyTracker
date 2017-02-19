package com.momomo.model;

import javax.persistence.Entity;

/**
 * Created by Monica on 2017-02-19.
 */
@Entity
public class Conference extends LearningMaterial{

    public Conference() {
        super();
    }

    public Conference(String name) {
        super(name);
    }
}
