package com.momomo.control;

import com.momomo.model.User;
import com.momomo.view.MainPage;
import com.vaadin.ui.*;
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
public class RemoveUserEventListener implements Button.ClickListener{

    private final UserRepositoryInterface userRepo;
    private final MainPage page;
    private ListSelect list;

    //Pass in text fields to get values at moment of click
    public RemoveUserEventListener(UserRepositoryInterface userRepo, MainPage page, ListSelect list) {
        this.userRepo = userRepo;
        this.page = page;
        this.list = list;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {
        String val = (String)list.getValue();
        userRepo.removeUser((String)list.getValue());
        page.updateUsersList(userRepo.getAllUsers());
    }

}
