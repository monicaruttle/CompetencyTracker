package com.momomo.model;

import javax.persistence.Entity;

/**
 * Created by Monica on 2017-02-19.
 */
@Entity
public class Book extends LearningMaterial {

    public Book() {
        super();
    }

    public Book(String name) {
        super(name);
    }
}
