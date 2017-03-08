package com.momomo.control;

import com.momomo.model.LearningMaterial;
import com.momomo.view.LearningMaterialManPage;
import com.momomo.view.MainPage;
import com.momomo.view.UserManPage;
import com.vaadin.ui.Button;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Charberg on 2/19/2017.
 */
@Getter
@Setter
public class AddLearningMaterialPopupEventListener implements Button.ClickListener{

    private final LearningMaterialManPage page;

    public AddLearningMaterialPopupEventListener(LearningMaterialManPage page) {
        this.page = page;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {
        page.displayAddLearningMaterialPopup();
    }

}
