package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.Role;
import com.momomo.model.User;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Charberg on 2/19/2017.
 */
@Getter
@Setter
@SpringUI
@org.springframework.stereotype.Component
public class MainPage extends UI {

    private final UserRepositoryInterface userRepo;
    private final SkillRepositoryInterface skillRepo;
    private final LearningMaterialRepositoryInterface materialRepo;
    //Level 1 MenuBar
    private MenuBar menuBar = new MenuBar();

    //Level 2 MenuItems
    private MenuBar.MenuItem userBar;
    private MenuBar.MenuItem skillBar;
    private MenuBar.MenuItem materialBar;
    private MenuBar.MenuItem logOut;

    //Sub-pages
    private UserManPage userManPage;
    private SkillManPage skillManPage;
    private AssignSubSkillsPage assignSubSkillsPage;
    private LearningMaterialManPage learningMaterialManPage;
    private AssignLearningMaterialsPage assignLearningMaterialsPage;

    private VerticalLayout layout = new VerticalLayout();

    private User currentUser;

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
        userManPage = new UserManPage(userRepo, skillRepo);
        userBar.addItem("Add/Remove/Inspect Users", (MenuBar.Command) selectedItem -> this.changeLayout(userManPage));

        skillBar = menuBar.addItem("Skill Management", null);
        skillManPage = new SkillManPage(userRepo, skillRepo, materialRepo);
        skillBar.addItem("Add/Remove Skills", (MenuBar.Command) selectedItem -> this.changeLayout(skillManPage));
        assignSubSkillsPage = new AssignSubSkillsPage(skillRepo);
        skillBar.addItem("Assign Subskills", (MenuBar.Command) selectedItem -> this.changeLayout(assignSubSkillsPage));

        materialBar = menuBar.addItem("Learning Material Management", null);
        learningMaterialManPage = new LearningMaterialManPage(userRepo, skillRepo, materialRepo);
        materialBar.addItem("Add/Remove Learning Materials", (MenuBar.Command) menuItem -> this.changeLayout(learningMaterialManPage));
        assignLearningMaterialsPage = new AssignLearningMaterialsPage(userRepo, skillRepo, materialRepo);
        materialBar.addItem("Assign Learning Materials", (MenuBar.Command) menuItem -> this.changeLayout(assignLearningMaterialsPage));

        menuBar.addItem("Log out", x -> this.changeLayout(new LoginPage(userRepo, skillRepo, this)));

        layout.addComponent(menuBar);
        layout.addComponent(new LoginPage(this.userRepo, this.skillRepo, this));
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

    public void setUserCreationVisibility(boolean visible) {
        userManPage.setUserCreationVisibility(visible);
    }

    public void setMenuBarVisibility(boolean value) {
        menuBar.setVisible(true);
        userBar.setVisible(value);
        skillBar.setVisible(value);
        materialBar.setVisible(value);
    }
    //TODO method to gray out remove buttons when respective lists are empty
}
