package com.momomo.control;

import com.momomo.view.UserManPage;
import com.vaadin.ui.Button;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Charberg on 2/19/2017.
 */
@Getter
@Setter
public class AddUserPopupEventListener implements Button.ClickListener{

    private final UserManPage page;

    public AddUserPopupEventListener(UserManPage page) {
        this.page = page;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {
        page.displayAddUserPopup();
    }

}
