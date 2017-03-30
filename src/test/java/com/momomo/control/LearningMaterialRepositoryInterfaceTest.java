package com.momomo.control;

import com.momomo.model.LearningMaterial;
import com.momomo.model.LearningMaterialRepository;
import com.momomo.model.MaterialType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Charberg on 2017-03-22.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LearningMaterialRepositoryInterfaceTest {

    @Autowired
    private
    LearningMaterialRepository repo;
    private LearningMaterialRepositoryInterface materialRepo;

    private LearningMaterial material1;
    private LearningMaterial material2;

    @Before
    public void setUp() {
        materialRepo = new LearningMaterialRepositoryInterface();
        materialRepo.setRepo(repo);
        material1 = new LearningMaterial("BOOK");
        material2 = new LearningMaterial("CD");
    }

    @Test
    public void addLearningMaterialTest() {
        this.materialRepo.addLearningMaterial(material1);
        assert(materialRepo.getAllLearningMaterials().contains(material1));
    }

    @Test
    public void getAllLearningMaterialsTest() {
        materialRepo.addLearningMaterial(material1);
        materialRepo.addLearningMaterial(material2);
        assert(materialRepo.getAllLearningMaterials().contains(material1));
        assert(materialRepo.getAllLearningMaterials().contains(material2));
    }

    @Test
    public void removeLearningMaterialTest() {
        this.materialRepo.addLearningMaterial(material1);
        assert(materialRepo.getAllLearningMaterials().contains(material1));
        materialRepo.removeLearningMaterial(material1.getName());
        assertEquals(false, materialRepo.getAllLearningMaterials().contains(material1));
    }

    @Test
    public void getLearningMaterialByNameTest() {
        this.materialRepo.addLearningMaterial(material1);
        assertEquals(material1, materialRepo.getLearningMaterialByName("BOOK"));
    }

    @Test
    public void updateMaterialTest() {
        material1.setType(MaterialType.BOOK);
        this.materialRepo.addLearningMaterial(material1);
        assertEquals(MaterialType.BOOK, materialRepo.getLearningMaterialByName("BOOK").getType());
        material1.setType(MaterialType.COURSE);
        materialRepo.updateLearningMaterial(material1);
        assertEquals(MaterialType.COURSE, materialRepo.getLearningMaterialByName("BOOK").getType());
    }

    @After
    public void tearDown() {
        try {
            materialRepo.removeLearningMaterial(material1.getName());
        } catch(Exception ignored) {

        }
        try {
            materialRepo.removeLearningMaterial(material2.getName());
        } catch(Exception ignored) {

        }
    }

}
