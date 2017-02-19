package com.momomo.control;

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
