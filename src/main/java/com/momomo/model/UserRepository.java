package com.momomo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by danielsauve on 2017-02-19.
 */

public interface UserRepository extends CrudRepository<User, String>{

    User findByUsername(String username);

    @Query(value = "DELETE FROM user_model_learning_materials WHERE user_model_username = :username;" +
            "DELETE FROM user_model where username = :username;", nativeQuery = true)
    void removeByUsername(@Param("username") String username);
}
