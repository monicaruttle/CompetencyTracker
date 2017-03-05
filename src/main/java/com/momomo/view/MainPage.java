package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.SkillRepository;
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


    private ListSelect userList;
    private final UserRepositoryInterface userRepo;
    private final SkillRepositoryInterface skillRepo;
    private MenuBar menuBar = new MenuBar();
    private MenuBar.MenuItem userBar;
    private MenuBar.MenuItem skillBar;
    private VerticalLayout layout = new VerticalLayout();
    private MenuBar.Command assignSkillsCommand;

    @Autowired
    public MainPage(UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepo) {
        this.userRepo = userRepositoryInterface;
        this.skillRepo = skillRepo;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setUpCommands(this);

        menuBar.setWidth(100, Unit.PERCENTAGE);
        userBar = menuBar.addItem("User Management", null);
        skillBar = menuBar.addItem("Skill Management", null);
        skillBar.addItem("Assign Skills", assignSkillsCommand);
        layout.addComponent(menuBar);
        layout.addComponent(new UserManPage(this.userRepo));
        this.setContent(layout);
    }

    public void setUpCommands(MainPage page) {

        this.assignSkillsCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                page.changeLayout(new AssignSkillsPage(userRepo, skillRepo));
            }
        };

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
