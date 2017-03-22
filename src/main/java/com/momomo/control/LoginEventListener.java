package com.momomo.control;

import com.momomo.model.User;
import com.momomo.view.MainPage;
import com.momomo.view.UserManPage;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * Created by Monica on 2017-03-16.
 */
public class LoginEventListener implements Button.ClickListener {

    private TextField username;
    private PasswordField password;
    private UserRepositoryInterface userRepositoryInterface;
    private SkillRepositoryInterface skillRepositoryInterface;
    private MainPage mainPage;

    public LoginEventListener(TextField username, PasswordField password, UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepositoryInterface, MainPage mainPage){
        this.username = username;
        this.password = password;
        this.userRepositoryInterface = userRepositoryInterface;
        this.skillRepositoryInterface = skillRepositoryInterface;
        this.mainPage = mainPage;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        User user = userRepositoryInterface.getUserByUserName(username.getValue());
        Notification error;

        if (user == null || !user.getPassword().equals(password.getValue())) {
            error = new Notification("Username or password is incorrect");
            error.setDelayMsec(2000);
            error.show(Page.getCurrent());
            return;
        }

        mainPage.setCurrentUserRole(user.getRole());
        mainPage.setMenuBarVisible(true);

        mainPage.changeLayout(new UserManPage(userRepositoryInterface, skillRepositoryInterface));
    }
}