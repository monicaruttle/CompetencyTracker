package com.momomo.control;

import com.momomo.model.Skill;
import com.vaadin.ui.Button;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by danielsauve on 2017-03-08.
 */
public class AssignSubSkillListener implements Button.ClickListener {
    private final ListSelect superSkillList;
    private final ListSelect subSkillList;
    private final SkillRepositoryInterface skillRepo;

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

        ArrayList<String> alreadyAddedSkills = new ArrayList<>();
        ArrayList<String> addedSkills = new ArrayList<>();
        boolean alreadyAdded = false;

        for (Skill subSkill : subSkills) {
            if(skill.containsSubSkill(subSkill)) {
                alreadyAddedSkills.add(subSkill.getName());
                alreadyAdded = true;
            }
            else {
                skill.addSubSkill(subSkill);
                addedSkills.add(subSkill.getName());
            }
        }

        String notify = "";

        if(alreadyAdded) {
            notify = notify + "The skill already has learned the following materials: " + String.join(",", alreadyAddedSkills);
        }

        if(addedSkills.size() > 0) notify = notify + "The skills now has the following subskills: " + String.join(",", addedSkills);

        skillRepo.updateSkill(skill);
        Notification.show(notify);
    }
}
