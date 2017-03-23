package com.momomo.control;

import com.momomo.model.Skill;
import com.momomo.model.SkillRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Monica on 2017-03-22.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SkillRepositoryInterfaceTest {

    @Autowired
    SkillRepository skillRepo;
    SkillRepositoryInterface skillRepoInterface;

    Skill programming;
    Skill hacking;

    @Before
    public void setUp() throws Exception {
        skillRepoInterface = new SkillRepositoryInterface();
        skillRepoInterface.setRepo(skillRepo);

        programming = new Skill("Programming");
        hacking = new Skill("Hacking");
    }

    @After
    public void tearDown() throws Exception {
        try {
            skillRepoInterface.removeSkill(programming.getName());
        } catch(Exception e) {

        }
        try {
            skillRepoInterface.removeSkill(hacking.getName());
        } catch(Exception e) {

        }
    }

    @Test
    public void addSkill() throws Exception {
        skillRepoInterface.addSkill(programming);
        assert(skillRepoInterface.getAllSkills().contains(programming));
    }

    @Test
    public void removeSkill() throws Exception {
        skillRepoInterface.addSkill(programming);
        assert(skillRepoInterface.getAllSkills().contains(programming));
        skillRepoInterface.removeSkill(programming.getName());
        assert(!skillRepoInterface.getAllSkills().contains(programming));
    }

    @Test
    public void updateSkill() throws Exception {
        Skill java = new Skill("Java");
        programming.addSubSkill(java);
        skillRepoInterface.addSkill(programming);
        assert(skillRepoInterface.getSkillByName(programming.getName()).containsSubSkill(java));
        programming.setSubSkills(new ArrayList<>());
        skillRepoInterface.updateSkill(programming);
        assert(!skillRepoInterface.getSkillByName(programming.getName()).containsSubSkill(java));
    }

    @Test
    public void getAllSkills() throws Exception {
        skillRepoInterface.addSkill(programming);
        skillRepoInterface.addSkill(hacking);
        assert(skillRepoInterface.getAllSkills().contains(programming));
        assert(skillRepoInterface.getAllSkills().contains(hacking));
    }

    @Test
    public void getSkillByName() throws Exception {
        skillRepoInterface.addSkill(programming);
        Skill programming2 = skillRepoInterface.getSkillByName(programming.getName());
        assertEquals(programming2, programming);
    }

}