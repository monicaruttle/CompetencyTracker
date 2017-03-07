package com.momomo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by danielsauve on 2017-02-19.
 * Represents a specific competency
 */
@Entity
@Getter
@Setter
public class Skill implements Comparable<Skill> {
    @Id
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> subSkills;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<LearningMaterial> learningMaterials;

    public Skill() {

        learningMaterials = new ArrayList<>();
        subSkills = new ArrayList<>();
    }

    public Skill(String name) {
        this.name = name;
        learningMaterials = new ArrayList<>();
        subSkills = new ArrayList<>();
    }

    public boolean containsLearningMaterial(LearningMaterial learn) {
        return learningMaterials.contains(learn);
    }

    public boolean addLearningMaterial(LearningMaterial learn) {
        if (learningMaterials.contains(learn))
            return false;
        learningMaterials.add(learn);
        return true;
    }

    public boolean containsSubSkill(Skill skill) {
        if (subSkills.contains(skill))
            return true;
        for(Skill s:subSkills) {
            boolean result = s.containsSubSkill(skill);
            if (result)
                return true;
        }

        return false;
    }

    public boolean addSubSkill(Skill skill) {
        if (!containsSubSkill(skill)) {
            subSkills.add(skill);
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        Skill s = (Skill)object;

        Collections.sort(s.subSkills);
        Collections.sort(this.subSkills);
        boolean equalSkills = s.subSkills.equals(this.subSkills);

        return (s.name.equals(this.name)) && equalSkills;
    }

    @Override
    public int compareTo(Skill other){
        return this.name.compareTo(other.name);
    }
}
