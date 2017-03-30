package com.momomo.control;

import com.momomo.model.Role;
import com.momomo.model.User;
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
    private TextField passwordField;
    private ComboBox cmbRole;
    private Window popup;

    //Pass in text fields to get values at moment of click
    public AddUserEventListener(UserRepositoryInterface userRepo, UserManPage page, TextField userNameField, TextField fullNameField, TextField passwordField, ComboBox cmbRole, Window popup) {
        this.userRepo = userRepo;
        this.page = page;
        this.userNameField = userNameField;
        this.fullNameField = fullNameField;
        this.passwordField = passwordField;
        this.cmbRole = cmbRole;
        this.popup = popup;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {

        if (cmbRole.getValue() == null) {
            Notification.show("User role field cannot be empty");
            return;
        } else if (fullNameField.getValue().equals("")) {
            Notification.show("Name field cannot be empty");
            return;
        } else if (!userNameField.isValid() && !passwordField.isValid()) {
            Notification.show("Invalid Username and Password");
            return;
        } else if (!passwordField.isValid()) {
            Notification.show("Invalid Password");
            return;
        } else if (!userNameField.isValid()) {
            Notification.show("Invalid Username");
            return;
        }

        if(!userRepo.addUser(new User(userNameField.getValue(), fullNameField.getValue(), passwordField.getValue(), (Role)cmbRole.getValue()))){
            Notification.show("User Already Exists","The Username you entered is already taken", Notification.Type.ERROR_MESSAGE);
        }

        page.updateUsersList(userRepo.getAllUsers());
        page.getUI().removeWindow(popup);
    }
}