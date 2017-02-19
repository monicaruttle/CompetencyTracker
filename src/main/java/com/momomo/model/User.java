package com.momomo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielsauve on 2017-02-19.
 * This class will represent all the user information
 */
@Entity
@Getter
@Setter
public class User {

    @Id
    private String username;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable
    private List<Skill> skills;
    private String name;

    public User() {
        skills = new ArrayList<>();
    }

    public User(String username, String name) {
        this.username = username;
        this.name = name;

        skills = new ArrayList<>();
    }

    public boolean addSkill(Skill skill) {
        if (skills.contains(skill))
            return false;

        skills.add(skill);
        return true;
    }

    public boolean hasSkill(Skill skill) {
        for(Skill s:skills) {
            if (s.equals(skill) || s.containsSubSkill(skill))
                return true;
        }
        return false;
    }
}
