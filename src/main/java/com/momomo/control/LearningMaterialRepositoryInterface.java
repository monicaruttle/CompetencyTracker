package com.momomo.control;

import com.momomo.model.LearningMaterial;
import com.momomo.model.LearningMaterialRepository;
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
public class LearningMaterialRepositoryInterface {

    @Autowired
    private LearningMaterialRepository repo;


    public LearningMaterialRepositoryInterface() {

    }

    public boolean addLearningMaterial(LearningMaterial material) {
        if(repo.findByName(material.getName()) == null) {
            repo.save(material);
            return true;
        }
        return false;
    }

    public void removeLearningMaterial(String name) {
        repo.delete(name);
        return;
    }

    public List<LearningMaterial> getAllLearningMaterials() {
        ArrayList<LearningMaterial> list = new ArrayList<>();
        Iterable<LearningMaterial> iter = repo.findAll();
        iter.forEach(list::add);
        return list;
    }

    public LearningMaterial getLearningMaterialByName(String name) {
        return repo.findByName(name);
    }

}
