package com.momomo.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Monica on 2017-02-19.
 */
public class SkillTest {

    Skill coordination;
    Skill juggling;
    Skill performing;

    LearningMaterial book;
    LearningMaterial conference;

    @Before
    public void setUp() throws Exception {
        coordination = new Skill("Coordination");
        juggling = new Skill("Juggling");
        performing = new Skill("Performing");

        book = new LearningMaterial("How to be a performer", MaterialType.BOOK);
        conference = new LearningMaterial("Jugglers unite", MaterialType.CONFERENCE);
    }

    @Test
    public void containsLearningMaterial() throws Exception {
        juggling.addLearningMaterial(conference);
        assertTrue(juggling.containsLearningMaterial(conference));
    }

    @Test
    public void addLearningMaterial() throws Exception {
        juggling.addLearningMaterial(conference);
        assertTrue(juggling.containsLearningMaterial(conference));
    }

    @Test
    public void containsSubSkill() throws Exception {
        juggling.addSubSkill(coordination);
        performing.addSubSkill(juggling);
        assertTrue(performing.containsSubSkill(juggling));
        assertTrue(performing.containsSubSkill(coordination));
    }

    @Test
    public void addSubSkill() throws Exception {
        performing.addSubSkill(juggling);
        assertTrue(performing.containsSubSkill(juggling));
    }

    @Test
    public void equals() throws Exception {
        Skill performing2 = new Skill("Performing");
        Skill juggling2 = new Skill("Juggling");
        Skill coordination2 = new Skill("Coordination");

        juggling2.addSubSkill(coordination2);
        performing2.addSubSkill(juggling2);

        juggling.addSubSkill(coordination);
        performing.addSubSkill(juggling);

        assertTrue(performing2.equals(performing));
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Performing", performing.getName());
    }

    @Test
    public void getSubSkills() throws Exception {
        performing.addSubSkill(juggling);

        List<Skill> skills = performing.getSubSkills();
        assertTrue(skills.contains(juggling));
    }

    @Test
    public void getLearningMaterials() throws Exception {
        performing.addLearningMaterial(book);
        assertTrue(performing.containsLearningMaterial(book));
    }

    @Test
    public void setName() throws Exception {
        performing.setName("Test");
        assertTrue(performing.getName().equals("Test"));
    }

    @Test
    public void setSubSkills() throws Exception {
        List<Skill> test = new ArrayList<>();
        performing.setSubSkills(test);
        assertTrue(performing.getSubSkills().equals(test));
    }

    @Test
    public void setLearningMaterials() throws Exception {
        List<LearningMaterial> test = new ArrayList<>();
        performing.setLearningMaterials(test);
        assertTrue(performing.getLearningMaterials().equals(test));
    }

}