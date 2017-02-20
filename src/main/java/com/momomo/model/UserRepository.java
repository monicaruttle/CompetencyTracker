package com.momomo.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by danielsauve on 2017-02-19.
 */

public interface UserRepository extends CrudRepository<User, String>{

    User findByUsername(String username);
    void removeByUsername(String username);
}
