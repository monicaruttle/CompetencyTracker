package com.momomo.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Monica on 2017-02-19.
 */
public class LearningMaterialTest {

    LearningMaterial material1;
    LearningMaterial material2;
    LearningMaterial material3;
    LearningMaterial material4;
    Skill skill;

    @Before
    public void setUp() throws Exception {
        skill = new Skill("Programming");
        material1 = new LearningMaterial("Programming for Dummies", MaterialType.BOOK);
        material2 = new LearningMaterial("Programming for Dummies", MaterialType.COURSE);
        material3 = new LearningMaterial("Programming for Dummies", MaterialType.BOOK);
        material4 = new LearningMaterial("Programming for Advanced Users", MaterialType.BOOK);
        material1.addSkill(skill);
    }

    @Test
    public void equals() throws Exception {
        assertTrue(material1.equals(material3));
        assertFalse(material1.equals(material2));
        assertFalse(material1.equals(material4));
    }

    @Test
    public void setName() throws Exception {
        material1.setName("Test");
        assertTrue(material1.getName().equals("Test"));
    }

    @Test
    public void setType() throws Exception {
        material1.setType(MaterialType.CONFERENCE);
        assertTrue(material1.getType().equals(MaterialType.CONFERENCE));
    }

    @Test
    public void getName() throws Exception {
        material1.setName("Test");
        assertTrue(material1.getName().equals("Test"));
    }

    @Test
    public void getType() throws Exception {
        material1.setType(MaterialType.CONFERENCE);
        assertTrue(material1.getType().equals(MaterialType.CONFERENCE));
    }
    @Test
    public void containsSkill() throws Exception {
        assertTrue(material1.containsSkill(skill));
    }

    @Test
    public void addSkill() throws Exception {
        Skill test = new Skill("Advance Programming");
        assertTrue(material4.addSkill(test));

    }

    @Test
    public void removeSkill() throws Exception {
        material1.removeSkill(skill);
        assertFalse(material1.containsSkill(skill));
    }

    @Test
    public void setSkillList() throws Exception {
        List<Skill> skillList = new ArrayList<>();
        Skill test = new Skill("Testing");
        skillList.add(test);
        material1.setSkillList(skillList);
        assertTrue(material1.containsSkill(test));
    }

    @Test
    public void getSkillList() throws Exception {
        assertTrue(material1.getSkillList().contains(skill));
    }

}