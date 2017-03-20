package com.momomo.control;

import com.momomo.view.LearningMaterialManPage;
import com.vaadin.ui.*;
import lombok.Getter;
import lombok.Setter;


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
        materialRepo.removeLearningMaterial((String)list.getValue());
        page.updateLearningMaterialsList(materialRepo.getAllLearningMaterials());
    }

}
