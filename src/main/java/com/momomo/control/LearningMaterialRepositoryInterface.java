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

    /**
     * Add a learning material to the database
     * @param material Learning material to add
     * @return True if the material was successfully added
     */
    public boolean addLearningMaterial(LearningMaterial material) {
        if(repo.findByName(material.getName()) == null) {
            repo.save(material);
            return true;
        }
        return false;
    }

    /**
     * Update a learning material in the database
     * @param material Learning material to update
     * @return True if the learning material was updated successfully
     */
    public boolean updateLearningMaterial(LearningMaterial material) {
        if (repo.findByName(material.getName()) == null) {
            return false;
        }
        repo.save(material);
        return true;

    }

    /**
     * Remove a learning material from the database
     * @param name The name of the learning material to remove
     */
    public void removeLearningMaterial(String name) {
        repo.delete(name);
    }

    /**
     * Get a list of all the learning materials in the database
     * @return The list of all the learning material in the database.
     */
    public List<LearningMaterial> getAllLearningMaterials() {
        ArrayList<LearningMaterial> list = new ArrayList<>();
        Iterable<LearningMaterial> iter = repo.findAll();
        iter.forEach(list::add);
        return list;
    }

    /**
     * Get a learning material from the database with a specified name
     * @param name The name of the requested learning material
     * @return The learning material with the corresponding name
     */
    public LearningMaterial getLearningMaterialByName(String name) {
        return repo.findByName(name);
    }

}
