package com.momomo.view;

import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

/**
 * Created by Charberg on 2/19/2017.
 */
@SpringUI
public class TestPage extends UI {

    VerticalLayout layout = new VerticalLayout();

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

        ListSelect userList = new ListSelect();
        userList.setWidth(50, Unit.PERCENTAGE);


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

    //TODO method to gray out remove buttons when respective lists are empty
}