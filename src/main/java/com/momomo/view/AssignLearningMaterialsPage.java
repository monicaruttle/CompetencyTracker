package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.User;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
public class AssignLearningMaterialsPage extends VerticalLayout{

    private ListSelect userList;
    private ListSelect learningMaterialList;
    private UserRepositoryInterface userRepo;
    private LearningMaterialRepositoryInterface learningMaterialRepo;
    private HorizontalLayout layout = new HorizontalLayout();

    public AssignLearningMaterialsPage(UserRepositoryInterface userRepositoryInterface, LearningMaterialRepositoryInterface learningMaterialRepo) {
        this.userRepo = userRepositoryInterface;
        this.learningMaterialRepo = learningMaterialRepo;

        Label userTitle = new Label( "Assign Learning Materials To Users" );
        userTitle.addStyleName( "h1" );

        this.addComponent(userTitle);
        this.addComponent(layout);
        this.setWidth(100, Unit.PERCENTAGE);
        layout.setWidth(50, Unit.PERCENTAGE);

        Panel userBtnPanel = new Panel();
        FormLayout userBtnLayout = new FormLayout();

        Button assignSkillBtn = new Button("Assign to User");
        Button removeSkillBtn = new Button("Remove from User");
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
        //TODO Populate skill list with existing skills

        //TODO Add click listeners to buttons

        userBtnPanel.setContent(userBtnLayout);
        userBtnLayout.addComponent(assignSkillBtn);
        userBtnLayout.addComponent(removeSkillBtn);
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

        for(User user: users) {
            userList.addItem(user.getUsername());
        }

    }

}

