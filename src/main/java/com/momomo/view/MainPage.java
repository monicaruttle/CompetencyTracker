package com.momomo.view;

import com.momomo.control.AddUserEventListener;
import com.momomo.control.AddUserPopupEventListener;
import com.momomo.control.RemoveUserEventListener;
import com.momomo.control.UserRepositoryInterface;
import com.momomo.model.User;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Charberg on 2/19/2017.
 */
@SpringUI
@org.springframework.stereotype.Component
public class MainPage extends UI {

    private VerticalLayout layout = new VerticalLayout();
    private ListSelect userList;
    private final UserRepositoryInterface userRepo;

    @Autowired
    public MainPage(UserRepositoryInterface userRepositoryInterface) {
        this.userRepo = userRepositoryInterface;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        HorizontalLayout userLayout = new HorizontalLayout();

        Label userTitle = new Label( "User Modification System" );
        userTitle.addStyleName( "h1" );

        layout.addComponent(userTitle);
        layout.addComponent(userLayout);

        layout.setWidth(100, Unit.PERCENTAGE);
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

        HorizontalLayout skillLayout = new HorizontalLayout();

        Label skillTitle = new Label( "Skill Modification System" );
        skillTitle.addStyleName( "h1" );

        layout.addComponent(skillTitle);
        layout.addComponent(skillLayout);

        
        skillLayout.setWidth(100, Unit.PERCENTAGE);

        Panel skillBtnPanel = new Panel();
        FormLayout skillBtnLayout = new FormLayout();

        Button addSkillBtn = new Button("Add Skill");
        Button removeSkillBtn = new Button("Remove Skill");
        Button inspectSkillBtn = new Button("Inspect Skill");

        ListSelect skillList = new ListSelect();
        skillList.setWidth(50, Unit.PERCENTAGE);
        skillList.setNullSelectionAllowed(false);
        skillList.setMultiSelect(false);


        skillBtnPanel.setContent(skillBtnLayout);
        skillBtnLayout.addComponent(addSkillBtn);
        skillBtnLayout.addComponent(removeSkillBtn);
        skillBtnLayout.addComponent(inspectSkillBtn);
        skillLayout.addComponent(skillBtnLayout);
        skillLayout.addComponent(skillList);

        skillLayout.setExpandRatio(skillBtnLayout, 0.2f);
        skillLayout.setExpandRatio(skillList, 0.8f);

        setContent(layout);
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
        btn.addClickListener(new AddUserEventListener(userRepo, this, userNameField, fullNameField));
        popupContent.addComponent(btn);
        popupContent.setVisible(true);

        // The component itself
        Window w = new Window();
        w.setContent(popupContent);
        this.addWindow(w);

    }

    public void updateUsersList(List<User> users) {

        userList.removeAllItems();

        for(User user: users) {
            userList.addItem(user.getUsername());
        }

    }


    //TODO method to gray out remove buttons when respective lists are empty
}