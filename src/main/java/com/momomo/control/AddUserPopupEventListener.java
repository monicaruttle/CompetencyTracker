package com.momomo.control;

import com.momomo.view.MainPage;
import com.vaadin.ui.Button;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Charberg on 2/19/2017.
 */
@Getter
@Setter
public class AddUserPopupEventListener implements Button.ClickListener{

    private final MainPage page;

    public AddUserPopupEventListener(MainPage page) {
        this.page = page;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {
        page.displayAddUserPopup();
    }

}
