package com.momomo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
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
        boolean equalSkills = s.subSkills.containsAll(this.subSkills) && this.subSkills.containsAll(s.subSkills);
        return (s.name.equals(this.name)) && equalSkills;
    }

    @Override
    public int compareTo(Skill other){
        return this.name.compareTo(other.name);
    }
}
