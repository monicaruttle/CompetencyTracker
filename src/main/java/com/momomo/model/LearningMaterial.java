package com.momomo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielsauve on 2017-02-19.
 * Represents the learning materials
 */

@Entity
@Inheritance
@Getter
@Setter
public abstract class LearningMaterial {

    @Id
    private String name;

    public LearningMaterial() { }

    public LearningMaterial(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        LearningMaterial lm = (LearningMaterial)object;

        return (lm.name == this.name);
    }

}