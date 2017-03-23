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
public class AssignSubSkillListener implements Button.ClickListener {
    private ListSelect superSkillList;
    private ListSelect subSkillList;
    private SkillRepositoryInterface skillRepo;

    public AssignSubSkillListener(ListSelect superSkillList, ListSelect subSkillList, SkillRepositoryInterface skillRepo) {
        this.superSkillList = superSkillList;
        this.subSkillList = subSkillList;
        this.skillRepo = skillRepo;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String skillName = (String) superSkillList.getValue();
        Skill skill = skillRepo.getSkillByName(skillName);

        ArrayList<Skill> subSkills = new ArrayList<>();
        Collection<String> subSkillNames = (Collection<String>) subSkillList.getValue();
        for (String s : subSkillNames){
            subSkills.add(skillRepo.getSkillByName(s));
        }

        ArrayList<String> alreadyAddedMaterials = new ArrayList<String>();
        boolean alreadyAdded = false;

        for (Skill subSkill : subSkills) {
            if(skill.containsSubSkill(subSkill)) {
                alreadyAddedMaterials.add(subSkill.getName());
                alreadyAdded = true;
            }
            else {
                skill.addSubSkill(subSkill);
            }
        }

        if(alreadyAdded) {
            Notification.show("Skill Already Has Sub-Skills ","The skill already has learned the following materials: " + String.join(",", alreadyAddedMaterials), Notification.Type.ERROR_MESSAGE);
        }
        skillRepo.updateSkill(skill);
    }
}
