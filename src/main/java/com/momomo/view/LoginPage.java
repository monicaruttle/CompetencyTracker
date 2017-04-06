package com.momomo.view;

import com.momomo.control.*;

import com.vaadin.ui.*;

/**
 * Created by Monica on 2017-03-16.
 */

class LoginPage extends VerticalLayout{

    public LoginPage(UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepositoryInterface, MainPage mainPage) {

        mainPage.getMenuBar().setVisible(false);

        // Create the user input field
        TextField username = new TextField("User:");
        username.setWidth("300px");
        username.setRequired(true);
        username.setInputPrompt("Your username");
        this.addComponent(username);

        // Create the password input field
        PasswordField password = new PasswordField("Password:");
        password.setWidth("300px");
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");
        this.addComponent(password);

        Button loginBtn = new Button("Login");
        loginBtn.setWidth("300px");
        loginBtn.addClickListener(new LoginEventListener(username, password, userRepositoryInterface, mainPage));

        username.addFocusListener(new EnterListener(loginBtn));
        password.addFocusListener(new EnterListener(loginBtn));

        this.addComponent(loginBtn);
    }
}