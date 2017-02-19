package com.momomo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class Book {

    @Id
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Skill> skills;

    public Book() {

    }

    public Book(String name) {
        this.name = name;
    }


}
