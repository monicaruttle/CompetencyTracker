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
        //TODO IMPLEMENT THIS METHOD
        //TODO ENSURE NO CIRCULAR DEPENDENCIES
    }
}
