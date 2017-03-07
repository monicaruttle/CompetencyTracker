package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.User;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
public class AssignSkillsPage extends VerticalLayout{

    private ListSelect userList;
    private ListSelect skillList;
    private UserRepositoryInterface userRepo;
    private SkillRepositoryInterface skillRepo;
    private HorizontalLayout layout = new HorizontalLayout();

    public AssignSkillsPage(UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepo) {
        this.userRepo = userRepositoryInterface;
        this.skillRepo = skillRepo;

        Label userTitle = new Label( "Assign Skills To Users" );
        userTitle.addStyleName( "h1" );

        this.addComponent(userTitle);
        this.addComponent(layout);
        this.setWidth(100, Unit.PERCENTAGE);
        layout.setWidth(50, Unit.PERCENTAGE);

        Panel userBtnPanel = new Panel();
        FormLayout userBtnLayout = new FormLayout();

        Button assignSkillBtn = new Button("<< Assign to User");
        Button removeSkillBtn = new Button(">> Remove from User");
        Button inspectUserBtn = new Button("Inspect User");

        userList = new ListSelect();
        userList.setWidth(100, Unit.PERCENTAGE);
        userList.setNullSelectionAllowed(false);
        userList.setMultiSelect(false);
        updateUsersList(userRepo.getAllUsers());

        skillList = new ListSelect();
        skillList.setWidth(100, Unit.PERCENTAGE);
        skillList.setNullSelectionAllowed(false);
        skillList.setMultiSelect(true);
        //TODO Populate skill list with existing skills

        //TODO Add click listeners to buttons

        userBtnPanel.setContent(userBtnLayout);
        userBtnLayout.addComponent(assignSkillBtn);
        userBtnLayout.addComponent(removeSkillBtn);
        userBtnLayout.addComponent(inspectUserBtn);
        layout.addComponent(userList);
        layout.addComponent(userBtnLayout);
        layout.addComponent(skillList);

        layout.setExpandRatio(userBtnLayout, 0.4f);
        layout.setExpandRatio(userList, 0.8f);
        layout.setExpandRatio(skillList, 0.8f);


    }



    public void updateUsersList(List<User> users) {

        userList.removeAllItems();

        for(User user: users) {
            userList.addItem(user.getUsername());
        }

    }

}

