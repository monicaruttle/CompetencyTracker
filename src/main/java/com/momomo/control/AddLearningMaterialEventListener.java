package com.momomo.control;

import com.momomo.model.LearningMaterial;
import com.momomo.model.User;
import com.momomo.view.LearningMaterialManPage;
import com.momomo.view.UserManPage;
import com.vaadin.ui.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Charberg on 2/19/2017.
 */
@Getter
@Setter
public class AddLearningMaterialEventListener implements Button.ClickListener{

    private final LearningMaterialRepositoryInterface materialRepo;
    private final LearningMaterialManPage page;
    private TextField nameField;
    private Window popup;

    //Pass in text fields to get values at moment of click
    public AddLearningMaterialEventListener(LearningMaterialRepositoryInterface materialRepo, LearningMaterialManPage page, TextField nameField, Window popup) {
        this.materialRepo = materialRepo;
        this.page = page;
        this.nameField = nameField;
        this.popup = popup;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {

        if(!materialRepo.addLearningMaterial(new LearningMaterial(nameField.getValue()))){
            Notification.show("Learning Material Already Exists","The name you entered is already taken", Notification.Type.ERROR_MESSAGE);
        }

        page.updateLearningMaterialsList(materialRepo.getAllLearningMaterials());
        page.getUI().removeWindow(popup);
    }

}
