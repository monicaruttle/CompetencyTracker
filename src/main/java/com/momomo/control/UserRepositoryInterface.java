package com.momomo.control;

import com.momomo.model.LearningMaterial;
import com.momomo.model.User;
import com.momomo.model.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charberg on 2/19/2017.
 * Wrapper for the user repository, for other methods interact with
 */
@Getter
@Setter
@Component
public class UserRepositoryInterface {

    @Autowired
    private UserRepository repo;


    public UserRepositoryInterface() {

    }

    /**
     * Add a user to the database
     * @param user The user to add to the database
     * @return True if the user was added successfully
     */
    public boolean addUser(User user) {
        if(repo.findByUsername(user.getUsername()) == null) {
            repo.save(user);
            return true;
        }
        return false;
    }

    /**
     * Update a user in the database
     * @param user The user to be updated
     * @return True if the user was updated successfully
     */
    public boolean updateUser(User user) {
        if (repo.findByUsername(user.getUsername()) == null) {
            return false;
        }
        repo.save(user);
        return true;

    }

    /**
     * Remove a user from the database
     * @param username The username of the user to be removed
     */
    public void removeUser(String username) {

        repo.delete(username);

    }

    /**
     * Get a list of all the users in the database
     * @return The list of all the users in the database
     */
    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        Iterable<User> iter = repo.findAll();
        iter.forEach(list::add);
        return list;
    }

    /**
     * Get a user from the database given a specified name
     * @param name The name of the requested user
     * @return The user with the specified name
     */
    public User getUserByUserName(String name) {
        return repo.findByUsername(name);
    }

    /**
     * Get a list of users that have the specified learning material
     * @param material The learning material assigned to the requested users
     * @return The list of users with the specified learning materials
     */
    public List<User> getUsersByLearningMaterial(LearningMaterial material) {

        ArrayList<User> list = new ArrayList<>();
        Iterable<User> iter = repo.findAll();
        for(User user: iter) {
            if(user.getLearningMaterials().contains(material)) {
                list.add(user);
            }
        }
        return list;
    }

}
