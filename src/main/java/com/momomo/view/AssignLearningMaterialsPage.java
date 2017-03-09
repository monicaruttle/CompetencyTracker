package com.momomo.view;

import com.momomo.control.AssignLearningMaterialListener;
import com.momomo.control.LearningMaterialRepositoryInterface;
import com.momomo.control.UserRepositoryInterface;
import com.momomo.model.LearningMaterial;
import com.momomo.model.User;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
public class AssignLearningMaterialsPage extends VerticalLayout {

    private ListSelect userList;
    private ListSelect learningMaterialList;
    private UserRepositoryInterface userRepo;
    private LearningMaterialRepositoryInterface learningMaterialRepo;
    private HorizontalLayout layout = new HorizontalLayout();

    public AssignLearningMaterialsPage(UserRepositoryInterface userRepositoryInterface, LearningMaterialRepositoryInterface learningMaterialRepo) {
        this.userRepo = userRepositoryInterface;
        this.learningMaterialRepo = learningMaterialRepo;

        Label userTitle = new Label("Assign Learning Materials To Users");
        userTitle.addStyleName("h1");

        this.addComponent(userTitle);
        this.addComponent(layout);
        this.setWidth(100, Unit.PERCENTAGE);
        layout.setWidth(50, Unit.PERCENTAGE);

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

        learningMaterialList = new ListSelect();
        learningMaterialList.setWidth(100, Unit.PERCENTAGE);
        learningMaterialList.setNullSelectionAllowed(false);
        learningMaterialList.setMultiSelect(true);
        updateLearningMaterialList(learningMaterialRepo.getAllLearningMaterials());

        assignLearningMaterial.addClickListener(new AssignLearningMaterialListener(userList, learningMaterialList, learningMaterialRepo, userRepositoryInterface));

        userBtnPanel.setContent(userBtnLayout);
        userBtnLayout.addComponent(assignLearningMaterial);
        userBtnLayout.addComponent(removeLearningMaterial);
        userBtnLayout.addComponent(inspectUserBtn);
        layout.addComponent(userList);
        layout.addComponent(userBtnLayout);
        layout.addComponent(learningMaterialList);

        layout.setExpandRatio(userBtnLayout, 0.4f);
        layout.setExpandRatio(userList, 0.8f);
        layout.setExpandRatio(learningMaterialList, 0.8f);


    }


    public void updateUsersList(List<User> users) {

        userList.removeAllItems();

        for (User user : users) {
            userList.addItem(user.getUsername());
        }

    }

    public void updateLearningMaterialList(List<LearningMaterial> learningMaterials) {
        this.learningMaterialList.removeAllItems();

        for (LearningMaterial learningMaterial : learningMaterials) {
            this.learningMaterialList.addItem(learningMaterial.getName());
        }
    }

}

