package com.momomo.control;

import com.momomo.model.User;
import com.momomo.view.MainPage;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * Created by Monica on 2017-03-16.
 */
public class LoginEventListener implements Button.ClickListener {

    private final TextField username;
    private final PasswordField password;
    private final UserRepositoryInterface userRepositoryInterface;
    private final MainPage mainPage;

    public LoginEventListener(TextField username, PasswordField password, UserRepositoryInterface userRepositoryInterface, MainPage mainPage){
        this.username = username;
        this.password = password;
        this.userRepositoryInterface = userRepositoryInterface;
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

        PrivilegeManager.SetVisibilities(user.getRole(), mainPage);

        mainPage.setCurrentUser(user);
        mainPage.getUserManPage().setCurrentUser(user);

        mainPage.changeLayout(mainPage.getUserManPage());
    }
}