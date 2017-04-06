package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.Skill;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
class AssignSubSkillsPage extends VerticalLayout {

    private final ListSelect superSkillList;
    private final ListSelect subSkillList;

    public AssignSubSkillsPage(SkillRepositoryInterface skillRepo) {

        Label subSkillTitle = new Label("Assign Sub Skills");
        subSkillTitle.addStyleName("h1");

        HorizontalLayout listTitleLayout = new HorizontalLayout();
        Label superSkillLabel = new Label("Super Skills");
        superSkillLabel.addStyleName("h2");
        Label subSkillLabel = new Label("Sub Skills");
        subSkillLabel.addStyleName("h2");
        listTitleLayout.addComponent(superSkillLabel);
        listTitleLayout.addComponent(subSkillLabel);
        listTitleLayout.setWidth(96 , Unit.PERCENTAGE);
        listTitleLayout.setComponentAlignment(subSkillLabel, Alignment.MIDDLE_RIGHT);

        this.addComponent(subSkillTitle);
        this.addComponent(listTitleLayout);
        HorizontalLayout assignToSkillLayout = new HorizontalLayout();
        this.addComponent(assignToSkillLayout);
        this.setWidth(100, Unit.PERCENTAGE);

        //ASSIGN TO SKILL LAYOUT//

        assignToSkillLayout.setWidth(80, Unit.PERCENTAGE);

        Panel BtnPanel = new Panel();
        FormLayout BtnLayout = new FormLayout();

        Button assignLearningMaterial = new Button("Assign to Super Skill");
        Button removeLearningMaterial = new Button("Remove from Super Skill");

        superSkillList = new ListSelect();
        superSkillList.setWidth(100, Unit.PERCENTAGE);
        superSkillList.setNullSelectionAllowed(false);
        superSkillList.setMultiSelect(false);

        subSkillList = new ListSelect();
        subSkillList.setWidth(100, Unit.PERCENTAGE);
        subSkillList.setNullSelectionAllowed(false);
        subSkillList.setMultiSelect(true);

        updateSkillLists(skillRepo.getAllSkills());

        assignLearningMaterial.addClickListener(new AssignSubSkillListener(superSkillList, subSkillList, skillRepo));

        BtnPanel.setContent(BtnLayout);
        BtnLayout.addComponent(assignLearningMaterial);
        BtnLayout.addComponent(removeLearningMaterial);
        assignToSkillLayout.addComponent(superSkillList);
        assignToSkillLayout.addComponent(BtnLayout);
        assignToSkillLayout.addComponent(subSkillList);

        assignToSkillLayout.setExpandRatio(BtnLayout, 0.4f);
        assignToSkillLayout.setExpandRatio(superSkillList, 0.8f);
        assignToSkillLayout.setExpandRatio(subSkillList, 0.8f);

    }

    public void updateSkillLists(List<Skill> skills) {

        superSkillList.removeAllItems();
        subSkillList.removeAllItems();

        //Both lists will be the same
        for (Skill skill : skills) {
            superSkillList.addItem(skill.getName());
            subSkillList.addItem(skill.getName());
        }

    }

}

