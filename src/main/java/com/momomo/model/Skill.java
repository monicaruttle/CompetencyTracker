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
 * Represents a specific competency
 */
@Entity
@Getter
@Setter
public class Skill {
    @Id
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> subSkills;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<LearningMaterial> learningMaterials;

    public Skill() {
        learningMaterials = new ArrayList<>();
    }

    public Skill(String name) {
        this.name = name;
        learningMaterials = new ArrayList<>();
        subSkills = new ArrayList<>();
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
        return (s.name == this.name);
    }
}
