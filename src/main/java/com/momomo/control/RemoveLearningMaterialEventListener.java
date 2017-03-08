package com.momomo.control;

import com.momomo.model.LearningMaterial;
import com.momomo.model.User;
import com.momomo.view.LearningMaterialManPage;
import com.momomo.view.MainPage;
import com.momomo.view.UserManPage;
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
public class RemoveLearningMaterialEventListener implements Button.ClickListener{

    private final LearningMaterialRepositoryInterface materialRepo;
    private final LearningMaterialManPage page;
    private ListSelect list;

    //Pass in text fields to get values at moment of click
    public RemoveLearningMaterialEventListener(LearningMaterialRepositoryInterface materialRepo, LearningMaterialManPage page, ListSelect list) {
        this.materialRepo = materialRepo;
        this.page = page;
        this.list = list;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {
        String val = (String)list.getValue();
        materialRepo.removeLearningMaterial((String)list.getValue());
        page.updateLearningMaterialsList(materialRepo.getAllLearningMaterials());
    }

}
