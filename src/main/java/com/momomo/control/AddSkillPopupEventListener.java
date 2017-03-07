package com.momomo.control;

import com.momomo.view.UserManPage;
import com.vaadin.ui.Button;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Monica on 2017-03-06.
 */
@Getter
@Setter
public class AddSkillPopupEventListener implements Button.ClickListener{

    private final UserManPage page;

    public AddSkillPopupEventListener(UserManPage page) {
        this.page = page;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {
        page.displayAddSkillPopup();
    }

}
