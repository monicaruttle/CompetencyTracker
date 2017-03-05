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


    private ListSelect userList;
    private final UserRepositoryInterface userRepo;
    private MenuBar menuBar = new MenuBar();
    private MenuBar.MenuItem userBar;
    private MenuBar.MenuItem skillBar;
    private VerticalLayout layout = new VerticalLayout();

    @Autowired
    public MainPage(UserRepositoryInterface userRepositoryInterface) {
        this.userRepo = userRepositoryInterface;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        menuBar.setWidth(100, Unit.PERCENTAGE);
        userBar = menuBar.addItem("User Management", null);
        skillBar = menuBar.addItem("Skill Management", null);
        skillBar.addItem("Assign Skills", null);

        layout.addComponent(menuBar);
        layout.addComponent(new UserManPage(this.userRepo));
        this.setContent(layout);
    }




    //TODO method to gray out remove buttons when respective lists are empty
}
