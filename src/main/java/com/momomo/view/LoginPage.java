package com.momomo.view;

import com.momomo.control.*;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import java.awt.event.FocusAdapter;

import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Created by Monica on 2017-03-16.
 */

public class LoginPage extends VerticalLayout{

    private TextField username;
    private PasswordField password;
    private Button loginBtn;

    private MainPage mainPage;

    public LoginPage(UserRepositoryInterface userRepositoryInterface, MainPage mainPage) {

        this.mainPage = mainPage;

        // Create the user input field
        username = new TextField("User:");
        username.setWidth("300px");
        username.setRequired(true);
        username.setInputPrompt("Your username (eg. joe@email.com)");
        username.addValidator(new EmailValidator("Username must be an email address"));
        this.addComponent(username);

        // Create the password input field
        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.addValidator(new PasswordValidator("Invalid Password"));
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");
        this.addComponent(password);

        loginBtn = new Button("Login");
        loginBtn.setWidth("300px");
        loginBtn.addClickListener(new LoginEventListener(username.getValue(), password.getValue(), userRepositoryInterface, mainPage));
        this.addComponent(loginBtn);
    }
}