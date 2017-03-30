package com.momomo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielsauve on 2017-02-19.
 * Represents a specific competency
 */
@Entity
@Getter
@Setter
@PersistenceContext(type = PersistenceContextType.EXTENDED)
@Proxy(lazy=false)
public class Skill implements Comparable<Skill> {
    @Id
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> subSkills;

    public Skill() {
        subSkills = new ArrayList<>();
    }

    public Skill(String name) {
        this.name = name;
        subSkills = new ArrayList<>();
    }

    public boolean containsSubSkill(Skill skill) {
        if (subSkills.contains(skill))
            return true;
        for(Skill s:subSkills) {
            if (s.containsSubSkill(skill)) {
                return true;
            }
        }

        return false;
    }

    public boolean addSubSkill(Skill skill) {
        if (!this.containsSubSkill(skill) && !this.equals(skill) && !skill.containsSubSkill(this)) {
            subSkills.add(skill);
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (!(object instanceof Skill))
            return false;
        Skill s = (Skill)object;
        return s.name.equals(this.name);
    }

    @Override
    public int compareTo(Skill other){
        return this.name.compareTo(other.name);
    }
}
