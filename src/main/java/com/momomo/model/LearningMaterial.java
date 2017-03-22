package com.momomo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> skillList;

    public LearningMaterial() {
        this.skillList = new ArrayList<>();
    }

    public LearningMaterial(String name) {
        this.skillList = new ArrayList<>();
        this.name = name;
        this.type = MaterialType.BOOK;
    }

    public LearningMaterial(String name, MaterialType type) {
        this.skillList = new ArrayList<>();
        this.name = name;
        this.type = type;
    }


    public boolean containsSkill(Skill skill) {
        return this.skillList.contains(skill);
    }

    public boolean addSkill(Skill skill) {
        if (this.skillList.contains(skill))
            return false;
        this.skillList.add(skill);
        return true;
    }

    public void removeSkill(Skill skill) {
        this.skillList.remove(skill);
    }


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LearningMaterial)) {
            return false;
        }
        if (object == this)
            return true;

        LearningMaterial lm = (LearningMaterial)object;

        return (lm.name.equals(this.name) && lm.type.equals(this.type));
    }

}