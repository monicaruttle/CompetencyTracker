package com.momomo.control;

import com.momomo.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.ServletConfig;

import static org.junit.Assert.*;
/**
 * Created by puih123 on 2017-03-21.
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryInterfaceTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private LearningMaterialRepository lRepo;

    private User user1;
    private User user2;

    private UserRepositoryInterface userRepo;
    private LearningMaterialRepositoryInterface materialRepo;


    @Before
    public void setUp() {
        user1 = new User("username", "fullname", "password", Role.BASIC);
        user2 = new User("user2", "full2", "pass2", Role.ADMIN);
        this.userRepo = new UserRepositoryInterface();
        this.userRepo.setRepo(repo);
        this.materialRepo = new LearningMaterialRepositoryInterface();
        this.materialRepo.setRepo(lRepo);
    }

    @Test
    public void addUser() {
        userRepo.addUser(user1);
        assertEquals(user1, userRepo.getUserByUserName("username"));
    }

    @Test
    public void getAllUsers() {
        userRepo.addUser(user1);
        userRepo.addUser(user2);
        assert(userRepo.getAllUsers().contains(user1));
        assert(userRepo.getAllUsers().contains(user2));
    }

    @Test
    public void removeUser() {
        userRepo.addUser(user1);
        assertEquals(user1, userRepo.getUserByUserName("username"));
        userRepo.removeUser("username");
        assertEquals(false, userRepo.getAllUsers().contains(user1));

    }

    @Test
    public void getUserByLearningMaterial() {
        LearningMaterial m = new LearningMaterial("BOOK");
        this.materialRepo.addLearningMaterial(m);
        user1.addLearningMaterial(m);
        userRepo.addUser(user1);
        assertEquals(1, userRepo.getUsersByLearningMaterial(m).size());
        assertEquals(user1, userRepo.getUsersByLearningMaterial(m).get(0));
    }

    @Test
    public void updateUser(){
        LearningMaterial m = new LearningMaterial("BOOK");
        LearningMaterial m2 = new LearningMaterial("CD");
        this.materialRepo.addLearningMaterial(m);
        this.materialRepo.addLearningMaterial(m2);
        user1.addLearningMaterial(m);
        userRepo.addUser(user1);
        assertEquals(1, userRepo.getUsersByLearningMaterial(m).size());
        assertEquals(user1, userRepo.getUsersByLearningMaterial(m).get(0));
        user1.addLearningMaterial(m2);
        userRepo.updateUser(user1);
        assertEquals(1, userRepo.getUsersByLearningMaterial(m2).size());
        assertEquals(user1, userRepo.getUsersByLearningMaterial(m2).get(0));
        user1.setName("change");
        userRepo.updateUser(user1);
        assertEquals("change", userRepo.getUserByUserName("username").getName());
        user1.setPassword("hunter2");
        userRepo.updateUser(user1);
        assertEquals("hunter2", userRepo.getUserByUserName("username").getPassword());
    }

}
