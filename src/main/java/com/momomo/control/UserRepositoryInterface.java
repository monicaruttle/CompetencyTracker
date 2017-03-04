package com.momomo.control;

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

    public boolean addUser(User user) {
        if(repo.findByUsername(user.getUsername()) == null) {
            repo.save(user);
            return true;
        }
        return false;
    }

    public void removeUser(String username) {

        repo.delete(username);

        return;
    }

    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<User>();
        Iterable<User> iter = repo.findAll();
        iter.forEach(list::add);
        return list;
    }


}
