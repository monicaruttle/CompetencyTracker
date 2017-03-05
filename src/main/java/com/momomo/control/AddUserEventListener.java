package com.momomo.control;

import com.momomo.model.User;
import com.momomo.view.MainPage;
import com.momomo.view.UserManPage;
import com.vaadin.ui.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Charberg on 2/19/2017.
 */
@Getter
@Setter
public class AddUserEventListener implements Button.ClickListener{

    private final UserRepositoryInterface userRepo;
    private final UserManPage page;
    private TextField userNameField;
    private TextField fullNameField;
    private Window popup;

    //Pass in text fields to get values at moment of click
    public AddUserEventListener(UserRepositoryInterface userRepo, UserManPage page, TextField userNameField, TextField fullNameField, Window popup) {
        this.userRepo = userRepo;
        this.page = page;
        this.userNameField = userNameField;
        this.fullNameField = fullNameField;
        this.popup = popup;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {

        if(!userRepo.addUser(new User(userNameField.getValue(), fullNameField.getValue()))){
            Notification.show("User Already Exists","The Username you entered is already taken", Notification.Type.ERROR_MESSAGE);
        }

        page.updateUsersList(userRepo.getAllUsers());
        page.getUI().removeWindow(popup);
    }

}
