package com.momomo.model;

import javax.persistence.Entity;

/**
 * Created by Monica on 2017-02-19.
 */
@Entity
public class Course extends LearningMaterial {

    public Course() {
        super();
    }

    public Course(String name) {
        super(name);
    }
}
