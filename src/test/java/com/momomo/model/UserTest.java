package com.momomo.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by danielsauve on 2017-02-19.
 */
public class UserTest {
    private User user;

    @Test
    public void setUsername() throws Exception {
        this.user.setUsername("Test");
        assertEquals("Test", this.user.getUsername());
    }

    @Test
    public void setName() throws Exception {
        this.user.setName("Test");
        assertEquals("Test", this.user.getName());
    }

    @Test
    public void getUsername() throws Exception {
        assertEquals("TestUser", this.user.getUsername());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("TestName", this.user.getName());
    }

    @Before
    public void setUp() {
        this.user = new User("TestUser", "TestName", Role.ACCESSOR);
    }

}