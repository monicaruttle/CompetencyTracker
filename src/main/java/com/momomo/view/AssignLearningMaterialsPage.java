package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.LearningMaterial;
import com.momomo.model.Skill;
import com.momomo.model.User;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
public class AssignLearningMaterialsPage extends VerticalLayout {

    private ListSelect userList;
    private ListSelect skillList;
    //Learning Material List for assigning to user
    private ListSelect learningMaterialList1;
    //Learning Material list for assigning to skill
    private ListSelect learningMaterialList2;
    private UserRepositoryInterface userRepo;
    private SkillRepositoryInterface skillRepo;
    private LearningMaterialRepositoryInterface learningMaterialRepo;
    private HorizontalLayout assignToUserLayout = new HorizontalLayout();
    private HorizontalLayout assignToSkillLayout = new HorizontalLayout();

    public AssignLearningMaterialsPage(UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepo, LearningMaterialRepositoryInterface learningMaterialRepo) {
        this.userRepo = userRepositoryInterface;
        this.skillRepo = skillRepo;
        this.learningMaterialRepo = learningMaterialRepo;

        Label userTitle = new Label("Assign Learning Materials To Users");
        userTitle.addStyleName("h1");

        this.addComponent(userTitle);
        this.addComponent(assignToUserLayout);
        this.setWidth(100, Unit.PERCENTAGE);

        //ASSIGN TO USER LAYOUT//

        assignToUserLayout.setWidth(50, Unit.PERCENTAGE);

        Panel userBtnPanel = new Panel();
        FormLayout userBtnLayout = new FormLayout();

        Button assignLearningMaterial = new Button("Assign to User");
        Button removeLearningMaterial = new Button("Remove from User");
        Button inspectUserBtn = new Button("Inspect User");

        userList = new ListSelect();
        userList.setWidth(100, Unit.PERCENTAGE);
        userList.setNullSelectionAllowed(false);
        userList.setMultiSelect(false);
        updateUsersList(userRepo.getAllUsers());

        learningMaterialList1 = new ListSelect();
        learningMaterialList1.setWidth(100, Unit.PERCENTAGE);
        learningMaterialList1.setNullSelectionAllowed(false);
        learningMaterialList1.setMultiSelect(true);

        assignLearningMaterial.addClickListener(new AssignLearningMaterialListener(userList, learningMaterialList1, learningMaterialRepo, userRepositoryInterface));

        userBtnPanel.setContent(userBtnLayout);
        userBtnLayout.addComponent(assignLearningMaterial);
        userBtnLayout.addComponent(removeLearningMaterial);
        userBtnLayout.addComponent(inspectUserBtn);
        assignToUserLayout.addComponent(userList);
        assignToUserLayout.addComponent(userBtnLayout);
        assignToUserLayout.addComponent(learningMaterialList1);

        assignToUserLayout.setExpandRatio(userBtnLayout, 0.4f);
        assignToUserLayout.setExpandRatio(userList, 0.8f);
        assignToUserLayout.setExpandRatio(learningMaterialList1, 0.8f);


        //ASSIGN TO SKILL LAYOUT//

        Label assignToSkillTitle = new Label("Assign Learning Materials To Skills");
        assignToSkillTitle.addStyleName("h1");

        this.addComponent(assignToSkillTitle);
        this.addComponent(assignToSkillLayout);

        assignToSkillLayout.setWidth(50, Unit.PERCENTAGE);

        Panel skillBtnPanel = new Panel();
        FormLayout skillBtnLayout = new FormLayout();

        Button assignMaterialToSkill = new Button("Assign to Skill");
        Button removeMaterialFromSkill = new Button("Remove from Skill");

        skillList = new ListSelect();
        skillList.setWidth(100, Unit.PERCENTAGE);
        skillList.setNullSelectionAllowed(false);
        skillList.setMultiSelect(false);
        updateSkillsList(skillRepo.getAllSkills());

        learningMaterialList2 = new ListSelect();
        learningMaterialList2.setWidth(100, Unit.PERCENTAGE);
        learningMaterialList2.setNullSelectionAllowed(false);
        learningMaterialList2.setMultiSelect(true);
        updateLearningMaterialList(learningMaterialRepo.getAllLearningMaterials());

        assignMaterialToSkill.addClickListener(new AssignMaterialToSkillListener(skillList, learningMaterialList2, learningMaterialRepo, skillRepo));
        removeMaterialFromSkill.addClickListener(new RemoveMaterialFromSkillListener(skillList, learningMaterialList2, learningMaterialRepo, skillRepo));

        skillBtnPanel.setContent(skillBtnLayout);
        skillBtnLayout.addComponent(assignMaterialToSkill);
        skillBtnLayout.addComponent(removeMaterialFromSkill);
        assignToSkillLayout.addComponent(skillList);
        assignToSkillLayout.addComponent(skillBtnLayout);
        assignToSkillLayout.addComponent(learningMaterialList2);

        assignToSkillLayout.setExpandRatio(skillBtnLayout, 0.4f);
        assignToSkillLayout.setExpandRatio(skillList, 0.8f);
        assignToSkillLayout.setExpandRatio(learningMaterialList2, 0.8f);


    }


    public void updateUsersList(List<User> users) {

        userList.removeAllItems();

        for (User user : users) {
            userList.addItem(user.getUsername());
        }

    }


    public void updateSkillsList(List<Skill> skills) {

        skillList.removeAllItems();

        for (Skill skill : skills) {
            skillList.addItem(skill.getName());
        }

    }

    public void updateLearningMaterialList(List<LearningMaterial> learningMaterials) {
        this.learningMaterialList1.removeAllItems();
        this.learningMaterialList2.removeAllItems();

        for (LearningMaterial learningMaterial : learningMaterials) {
            this.learningMaterialList1.addItem(learningMaterial.getName());
            this.learningMaterialList2.addItem(learningMaterial.getName());
        }
    }

}

