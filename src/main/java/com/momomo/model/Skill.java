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
    }
}
