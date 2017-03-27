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
        ArrayList<String> addedMaterials = new ArrayList<String>();
        boolean alreadyAdded = false;

        for (LearningMaterial material : learningMaterials) {
            if(material.containsSkill(skill)) {
                alreadyAddedMaterials.add(material.getName());
                alreadyAdded = true;
            }
            else {
                material.addSkill(skill);
                addedMaterials.add(skill.getName());
            }
            materialRepo.updateLearningMaterial(material);
        }

        String notify = "";

        if(alreadyAdded) {
            notify = notify + "The skill already has the following materials assigned: " + String.join(",", alreadyAddedMaterials) + "\n";
        }

        if(addedMaterials.size() > 0) notify = notify + "The skill has now been assigned the following materials: " + String.join(",", addedMaterials);

        skillRepo.updateSkill(skill);
        Notification.show(notify, Notification.Type.WARNING_MESSAGE);

    }
}
