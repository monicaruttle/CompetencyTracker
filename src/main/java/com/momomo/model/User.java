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
@Entity(name = "user_model")
@Getter
@Setter
public class User {

    @Id
    private String username;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable
    private List<LearningMaterial> learningMaterials;
    private String name;
    private Role role;
    private String password;

    public User() {
        this.learningMaterials = new ArrayList<>();
    }

    public User(String username, String name, String password, Role role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;

        this.learningMaterials = new ArrayList<>();
    }

    public boolean addLearningMaterial(LearningMaterial learningMaterial) {
        if (this.learningMaterials.contains(learningMaterial)) {
            return false;
        }
        this.learningMaterials.add(learningMaterial);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof User)) {
            return false;
        }

        User user = (User) obj;

        return user.getUsername().equals(this.getUsername());
    }
}
