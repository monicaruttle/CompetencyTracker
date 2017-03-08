package com.momomo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by danielsauve on 2017-02-19.
 * Represents the learning materials
 */

@Entity
@Getter
@Setter
public class LearningMaterial {

    @Id
    private String name;

    private MaterialType type;

    public LearningMaterial() { }

    public LearningMaterial(String name) {
        this.name = name;
        this.type = MaterialType.BOOK;
    }

    public LearningMaterial(String name, MaterialType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        LearningMaterial lm = (LearningMaterial)object;

        return (lm.name.equals(this.name) && lm.type.equals(this.type));
    }

}