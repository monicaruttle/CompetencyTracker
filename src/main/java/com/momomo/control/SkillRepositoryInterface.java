package com.momomo.control;

import com.momomo.model.Skill;
import com.momomo.model.SkillRepository;
import com.momomo.model.User;
import com.momomo.model.UserRepository;
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

    public boolean addSkill(Skill skill) {
        if(repo.findByName(skill.getName()) == null) {
            repo.save(skill);
            return true;
        }
        return false;
    }

    public void removeSkill(String name) {

        repo.delete(name);

        return;
    }

    public boolean updateSkill(Skill skill) {
        if (repo.findByName(skill.getName()) == null) {
            return false;
        }
        repo.save(skill);
        return true;

    }

    public List<Skill> getAllSkills() {
        ArrayList<Skill> list = new ArrayList<>();
        Iterable<Skill> iter = repo.findAll();
        iter.forEach(list::add);
        return list;
    }

    public Skill getSkillByName(String name) {
        return repo.findByName(name);
    }


}
