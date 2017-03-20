package com.momomo.control;

import com.momomo.view.MainPage;
import com.momomo.view.UserManPage;
import com.vaadin.ui.Button;

/**
 * Created by Monica on 2017-03-16.
 */
public class LoginEventListener implements Button.ClickListener {

    private String username;
    private String password;
    private UserRepositoryInterface userRepositoryInterface;
    private SkillRepositoryInterface skillRepositoryInterface;
    private MainPage mainPage;

    public LoginEventListener(String username, String password, UserRepositoryInterface userRepositoryInterface, SkillRepositoryInterface skillRepositoryInterface, MainPage mainPage){
        this.username = username;
        this.password = password;
        this.userRepositoryInterface = userRepositoryInterface;
        this.skillRepositoryInterface = skillRepositoryInterface;
        this.mainPage = mainPage;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        //check if password and username match with the one in the repo.

        mainPage.setContent(new UserManPage(userRepositoryInterface, skillRepositoryInterface));
    }
}