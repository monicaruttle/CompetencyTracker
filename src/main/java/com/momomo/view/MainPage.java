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

    @Autowired
    public MainPage(UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepositoryInterface, LearningMaterialRepositoryInterface learningMaterialRepositoryInterface) {
        this.userRepo = userRepositoryInterface;
        this.skillRepo = skillRepositoryInterface;
        this.materialRepo = learningMaterialRepositoryInterface;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        menuBar.setWidth(100, Unit.PERCENTAGE);
        userBar = menuBar.addItem("User Management", null);
        userBar.addItem("Add/Remove Users", (MenuBar.Command) selectedItem -> this.changeLayout(new UserManPage(userRepo)));

        skillBar = menuBar.addItem("Skill Management", null);
        skillBar.addItem("Add/Remove Skills", (MenuBar.Command) selectedItem -> this.changeLayout(new SkillManPage(skillRepo)));

        materialBar = menuBar.addItem("Learning Material Management", null);
        materialBar.addItem("Add/Remove Learning Materials", (MenuBar.Command) menuItem -> this.changeLayout(new LearningMaterialManPage(materialRepo)));
        materialBar.addItem("Assign Learning Materials to Users", (MenuBar.Command) menuItem -> this.changeLayout(new AssignLearningMaterialsPage(userRepo, skillRepo, materialRepo)));
        layout.addComponent(menuBar);
        layout.addComponent(new UserManPage(this.userRepo));
        this.setContent(layout);
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
