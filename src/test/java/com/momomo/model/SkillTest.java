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

    private Skill coordination;
    private Skill juggling;
    private Skill performing;

    @Before
    public void setUp() throws Exception {
        coordination = new Skill("Coordination");
        juggling = new Skill("Juggling");
        performing = new Skill("Performing");
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
    public void addSelfSubSkill() throws Exception {
        assertFalse(coordination.addSubSkill(coordination));
    }

    @Test
    public void circularSubSkill() throws Exception {
        coordination.addSubSkill(juggling);
        juggling.addSubSkill(performing);
        assertFalse(performing.addSubSkill(coordination));
    }

}