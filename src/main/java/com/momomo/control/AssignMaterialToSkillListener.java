package com.momomo.control;

import com.momomo.model.LearningMaterial;
import com.momomo.model.Skill;
import com.momomo.model.User;
import com.vaadin.ui.Button;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by danielsauve on 2017-03-08.
 */
public class AssignMaterialToSkillListener implements Button.ClickListener {
    private ListSelect skillList;
    private ListSelect materialList;
    private LearningMaterialRepositoryInterface materialRepo;
    private SkillRepositoryInterface skillRepo;

    public AssignMaterialToSkillListener(ListSelect skillList, ListSelect materialList, LearningMaterialRepositoryInterface materialRepo, SkillRepositoryInterface skillRepo) {
        this.skillList = skillList;
        this.materialList = materialList;
        this.materialRepo = materialRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String skillName = (String) skillList.getValue();
        Skill skill = skillRepo.getSkillByName(skillName);

        ArrayList<LearningMaterial> learningMaterials = new ArrayList<>();
        Collection<String> learningMaterialNames = (Collection) materialList.getValue();
        for (String s : learningMaterialNames) {
            learningMaterials.add(materialRepo.getLearningMaterialByName(s));
        }

        ArrayList<String> alreadyAddedMaterials = new ArrayList<String>();
        boolean alreadyAdded = false;

        for (LearningMaterial material : learningMaterials) {
            if(skill.getLearningMaterials().contains(material)) {
                alreadyAddedMaterials.add(material.getName());
                alreadyAdded = true;
            }
            else {
                skill.addLearningMaterial(material);
            }
        }

        if(alreadyAdded) {
            Notification.show("Skill Already Has Learning Materials","The skill already has learned the following materials: " + String.join(",", alreadyAddedMaterials), Notification.Type.ERROR_MESSAGE);
        }

        skillRepo.updateSkill(skill);

    }
}
