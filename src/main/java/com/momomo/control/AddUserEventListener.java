package com.momomo.control;

import com.momomo.model.User;
import com.momomo.view.MainPage;
import com.vaadin.ui.Button;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.soap.Text;

/**
 * Created by Charberg on 2/19/2017.
 */
@Getter
@Setter
public class AddUserEventListener implements Button.ClickListener{

    private final UserRepositoryInterface userRepo;
    private final MainPage page;
    private TextField userNameField;
    private TextField fullNameField;

    //Pass in text fields to get values at moment of click
    public AddUserEventListener(UserRepositoryInterface userRepo, MainPage page, TextField userNameField, TextField fullNameField) {
        this.userRepo = userRepo;
        this.page = page;
        this.userNameField = userNameField;
        this.fullNameField = fullNameField;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {

        userRepo.addUser(new User(userNameField.getValue(), null, fullNameField.getValue()));

        page.updateUsersList(userRepo.getAllUsers());
    }

}
