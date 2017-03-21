package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.LearningMaterial;
import com.momomo.model.Role;
import com.momomo.model.Skill;
import com.momomo.model.User;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
public class UserManPage extends VerticalLayout{

    private ListSelect userList;
    private UserRepositoryInterface userRepo;
    private SkillRepositoryInterface skillRepo;

    public UserManPage(UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepo) {
        this.userRepo = userRepositoryInterface;
        this.skillRepo = skillRepo;

        HorizontalLayout userLayout = new HorizontalLayout();

        Label userTitle = new Label( "User Modification System" );
        userTitle.addStyleName( "h1" );

        this.addComponent(userTitle);
        this.addComponent(userLayout);

        this.setWidth(100, Unit.PERCENTAGE);
        userLayout.setWidth(100, Unit.PERCENTAGE);

        Panel userBtnPanel = new Panel();
        FormLayout userBtnLayout = new FormLayout();

        Button addUserBtn = new Button("Add User");
        Button removeUserBtn = new Button("Remove User");
        Button inspectUserBtn = new Button("Inspect User");

        userList = new ListSelect();
        userList.setWidth(50, Unit.PERCENTAGE);
        userList.setNullSelectionAllowed(false);
        userList.setMultiSelect(false);
        updateUsersList(userRepo.getAllUsers());

        addUserBtn.addClickListener(new AddUserPopupEventListener(this));
        removeUserBtn.addClickListener(new RemoveUserEventListener(userRepo, this, userList));
        inspectUserBtn.addClickListener(e -> this.displayInspectUserPopup());

        userBtnPanel.setContent(userBtnLayout);
        userBtnLayout.addComponent(addUserBtn);
        userBtnLayout.addComponent(removeUserBtn);
        userBtnLayout.addComponent(inspectUserBtn);
        userLayout.addComponent(userBtnLayout);
        userLayout.addComponent(userList);

        userLayout.setExpandRatio(userBtnLayout, 0.2f);
        userLayout.setExpandRatio(userList, 0.8f);

    }

    public void displayAddUserPopup() {

        //Create pop up for new user fields

        // Content for the PopupView
        VerticalLayout popupContent = new VerticalLayout();
        TextField userNameField = new TextField("Username");
        TextField fullNameField = new TextField("Full Name");
        TextField passwordField = new TextField("Password");

        // Selecting the user role
        List<Role> roles = new ArrayList<>();
        roles.addAll(EnumSet.allOf(Role.class));
        ComboBox cmbRole = new ComboBox("User Role");
        cmbRole.addItems(roles);

        popupContent.addComponent(userNameField);
        popupContent.addComponent(fullNameField);
        popupContent.addComponent(passwordField);
        popupContent.addComponent(cmbRole);
        Button btn = new Button("Submit");
        popupContent.addComponent(btn);
        popupContent.setVisible(true);

        // The component itself
        Window w = new Window();
        w.setContent(popupContent);
        btn.addClickListener(new AddUserEventListener(userRepo, this, userNameField, fullNameField, passwordField, cmbRole, w));
        this.getUI().addWindow(w);

    }

    public void displayInspectUserPopup() {

        if(userList.getValue() == null) {
            return;
        }

        VerticalLayout popupContent = new VerticalLayout();

        TextArea skillTextArea = new TextArea("User's skills:");
        TextArea materialTextArea = new TextArea("User's learned materials:");

        User user = userRepo.getUserByUserName((String)userList.getValue());

        String materialString = "";
        String skillString = "";

        for(LearningMaterial material : user.getLearningMaterials()) {
            materialString = materialString + material.getName() + "\n";

            for(Skill skill : skillRepo.getSkillsByLearningMaterial(material)) {
                skillString = skillString + skill.getName() + "\n";
            }
        }

        skillTextArea.setValue(skillString);
        materialTextArea.setValue(materialString);

        //Setting to read only must be done AFTER setting field content
        skillTextArea.setReadOnly(true);
        materialTextArea.setReadOnly(true);

        popupContent.addComponent(new Label("Username: " + user.getUsername()));
        popupContent.addComponent(new Label("Full Name: " + user.getName()));
        popupContent.addComponent(new Label("Role: " + user.getRole()));
        popupContent.addComponent(skillTextArea);
        popupContent.addComponent(materialTextArea);
        Button btn = new Button("OK");
        popupContent.addComponent(btn);
        popupContent.setVisible(true);

        Window w = new Window();

        w.setContent(popupContent);
        btn.addClickListener(e -> this.getUI().removeWindow(w));
        this.getUI().addWindow(w);
    }

    public void updateUsersList(List<User> users) {

        userList.removeAllItems();

        for(User user: users) {
            userList.addItem(user.getUsername());
        }

    }


}
