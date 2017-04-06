package com.momomo.control;

import com.momomo.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monica on 3/6/2017.
 * Wrapper for the user repository, for other methods interact with
 */
@Getter
@Setter
@Component
public class SkillRepositoryInterface {

    @Autowired
    private SkillRepository repo;


    public SkillRepositoryInterface() {

    }

    /**
     * Add a skill to the database
     * @param skill The skill to add to the database
     * @return True if the skill was added successfully
     */
    public boolean addSkill(Skill skill) {
        if(repo.findByName(skill.getName()) == null) {
            repo.save(skill);
            return true;
        }
        return false;
    }

    /**
     * Remove a skill from the database
     * @param name The name of the skill to be removed
     */
    public void removeSkill(String name) {

        repo.delete(name);

    }

    /**
     * Update a skill in the database
     * @param skill The skill to be updated
     * @return True if the skill was updated successfully
     */
    public boolean updateSkill(Skill skill) {
        if (repo.findByName(skill.getName()) == null) {
            return false;
        }
        repo.save(skill);
        return true;

    }

    /**
     * Get a list of all the skills in the database
     * @return The list of all the skills in the database
     */
    public List<Skill> getAllSkills() {
        ArrayList<Skill> list = new ArrayList<>();
        Iterable<Skill> iter = repo.findAll();
        iter.forEach(list::add);
        return list;
    }

    /**
     * Get a skill from the database given a specified name
     * @param name The name of the requested skill
     * @return The skill with the specified name
     */
    public Skill getSkillByName(String name) {
        return repo.findByName(name);
    }

}
