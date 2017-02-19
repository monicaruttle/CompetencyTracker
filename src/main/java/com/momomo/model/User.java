package com.momomo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    public User() { }

    public User(String username, List<Skill> skills, String name) {
        this.username = username;
        this.skills = skills;
        this.name = name;
    }
}
