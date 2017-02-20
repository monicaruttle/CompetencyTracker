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
    public void addSkill() throws Exception {
        Skill skill = new Skill("TestSkill");
        assertTrue(this.user.addSkill(skill));
    }

    @Test
    public void hasSkill() throws Exception {
        Skill skill = new Skill("TestSkill");
        this.user.addSkill(skill);
        assertTrue(this.user.hasSkill(skill));
    }

    @Test
    public void setUsername() throws Exception {
        this.user.setUsername("Test");
        assertEquals("Test", this.user.getUsername());
    }

    @Test
    public void setSkills() throws Exception {
        Skill skill1 = new Skill("TestSkill1");
        Skill skill2 = new Skill("TestSkill2");
        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill1);
        skillList.add(skill2);
        this.user.setSkills(skillList);
        assertEquals(2, this.user.getSkills().size());
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
    public void getSkills() throws Exception {
        assertEquals(0, this.user.getSkills().size());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("TestName", this.user.getName());
    }

    @Before
    public void setUp() {
        this.user = new User("TestUser", "TestName");
    }

}