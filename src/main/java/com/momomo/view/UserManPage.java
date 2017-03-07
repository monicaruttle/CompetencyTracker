package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.Skill;
import com.momomo.model.SkillRepository;
import com.momomo.model.User;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
public class UserManPage extends VerticalLayout{

    private ListSelect userList;
    private ListSelect skillList;
    private UserRepositoryInterface userRepo;
    private SkillRepositoryInterface skillRepo;

    public UserManPage(UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepositoryInterface) {
        this.userRepo = userRepositoryInterface;

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

        userBtnPanel.setContent(userBtnLayout);
        userBtnLayout.addComponent(addUserBtn);
        userBtnLayout.addComponent(removeUserBtn);
        userBtnLayout.addComponent(inspectUserBtn);
        userLayout.addComponent(userBtnLayout);
        userLayout.addComponent(userList);

        userLayout.setExpandRatio(userBtnLayout, 0.2f);
        userLayout.setExpandRatio(userList, 0.8f);

        /////////////////////////////////////////////////////////////////////////
        this.skillRepo = skillRepositoryInterface;

        HorizontalLayout skillLayout = new HorizontalLayout();

        Label skillTitle = new Label( "Skill Modification System" );
        skillTitle.addStyleName( "h1" );

        this.addComponent(skillTitle);
        this.addComponent(skillLayout);


        skillLayout.setWidth(100, Unit.PERCENTAGE);

        Panel skillBtnPanel = new Panel();
        FormLayout skillBtnLayout = new FormLayout();

        Button addSkillBtn = new Button("Add Skill");
        Button removeSkillBtn = new Button("Remove Skill");
        Button inspectSkillBtn = new Button("Inspect Skill");

        skillList = new ListSelect();
        skillList.setWidth(50, Unit.PERCENTAGE);
        skillList.setNullSelectionAllowed(false);
        skillList.setMultiSelect(false);
        updateSkillList(skillRepo.getAllSkills());

        addSkillBtn.addClickListener(new AddSkillPopupEventListener(this));
        removeSkillBtn.addClickListener(new RemoveSkillEventListener(skillRepo, this, skillList));

        skillBtnPanel.setContent(skillBtnLayout);
        skillBtnLayout.addComponent(addSkillBtn);
        skillBtnLayout.addComponent(removeSkillBtn);
        skillBtnLayout.addComponent(inspectSkillBtn);
        skillLayout.addComponent(skillBtnLayout);
        skillLayout.addComponent(skillList);

        skillLayout.setExpandRatio(skillBtnLayout, 0.2f);
        skillLayout.setExpandRatio(skillList, 0.8f);

    }

    public void displayAddUserPopup() {

        //Create pop up for new user fields

        // Content for the PopupView
        VerticalLayout popupContent = new VerticalLayout();
        TextField userNameField = new TextField("Username");
        TextField fullNameField = new TextField("Full Name");

        popupContent.addComponent(userNameField);
        popupContent.addComponent(fullNameField);
        Button btn = new Button("Submit");
        popupContent.addComponent(btn);
        popupContent.setVisible(true);

        // The component itself
        Window w = new Window();
        w.setContent(popupContent);
        btn.addClickListener(new AddUserEventListener(userRepo, this, userNameField, fullNameField, w));
        this.getUI().addWindow(w);

    }

    public void displayAddSkillPopup() {

        VerticalLayout popupContent = new VerticalLayout();
        TextField nameField = new TextField("Name");

        popupContent.addComponent(nameField);
        Button btn = new Button("Submit");
        popupContent.addComponent(btn);
        popupContent.setVisible(true);

        Window w = new Window();
        w.setContent(popupContent);
        btn.addClickListener(new AddSkillEventListener(skillRepo, this, nameField, w));
        this.getUI().addWindow(w);
    }

    public void updateUsersList(List<User> users) {

        userList.removeAllItems();

        for(User user: users) {
            userList.addItem(user.getUsername());
        }

    }

    public void updateSkillList(List<Skill> skills) {

        skillList.removeAllItems();

        for(Skill skill: skills) {
            skillList.addItem(skill.getName());
        }

    }

}
