package com.momomo.view;

import com.momomo.control.*;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Charberg on 2/19/2017.
 */
@SpringUI
@org.springframework.stereotype.Component
public class MainPage extends UI {

    private final UserRepositoryInterface userRepo;
    private final SkillRepositoryInterface skillRepo;
    private final LearningMaterialRepositoryInterface materialRepo;
    private MenuBar menuBar = new MenuBar();
    private MenuBar.MenuItem userBar;
    private MenuBar.MenuItem skillBar;
    private MenuBar.MenuItem materialBar;
    private VerticalLayout layout = new VerticalLayout();
    private MenuBar.Command assignLearningMaterialsCommand;
    private MenuBar.Command manageLearningMaterialsCommand;
    private MenuBar.Command userManagementCommand;

    @Autowired
    public MainPage(UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepositoryInterface, LearningMaterialRepositoryInterface learningMaterialRepositoryInterface) {
        this.userRepo = userRepositoryInterface;
        this.skillRepo = skillRepositoryInterface;
        this.materialRepo = learningMaterialRepositoryInterface;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setUpCommands();

        menuBar.setWidth(100, Unit.PERCENTAGE);
        userBar = menuBar.addItem("User Management", userManagementCommand);
        skillBar = menuBar.addItem("Skill Management", null);
        materialBar = menuBar.addItem("Learning Material Management", null);
        materialBar.addItem("Add/Remove Learning Materials", manageLearningMaterialsCommand);
        materialBar.addItem("Assign Learning Materials", assignLearningMaterialsCommand);
        layout.addComponent(menuBar);
        layout.addComponent(new UserManPage(this.userRepo, this.skillRepo));
        this.setContent(layout);
    }

    public void setUpCommands() {

        this.assignLearningMaterialsCommand = (MenuBar.Command) menuItem -> this.changeLayout(new AssignLearningMaterialsPage(userRepo, materialRepo));

        this.manageLearningMaterialsCommand = (MenuBar.Command) menuItem -> this.changeLayout(new LearningMaterialManPage(materialRepo));

        this.userManagementCommand = (MenuBar.Command) selectedItem -> this.changeLayout(new UserManPage(userRepo, skillRepo));

    }

    public void changeLayout(Layout layout) {

        this.layout.removeAllComponents();
        this.layout.addComponent(this.menuBar);
        this.layout.addComponent(layout);


    }

    public VerticalLayout getPageLayoutElement() {
        return this.layout;
    }


    //TODO method to gray out remove buttons when respective lists are empty
}
