package com.momomo.control;

import com.momomo.view.MainPage;
import com.vaadin.ui.Button;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Charberg on 2/19/2017.
 */
@Getter
@Setter
public class AddUserEventListener implements Button.ClickListener{

    private final UserRepositoryInterface userRepo;

    private final MainPage page;

    public AddUserEventListener(UserRepositoryInterface userRepo, MainPage page) {
        this.userRepo = userRepo;
        this.page = page;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {
        page.updateUsersList(userRepo.getAllUsers());
    }

}
