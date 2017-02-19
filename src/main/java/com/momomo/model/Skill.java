package com.momomo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    @ManyToMany
    private List<Skill> subSkills;

    public Skill() { }

    public Skill(String name){
        this.name = name;
    }
}
