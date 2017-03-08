package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.LearningMaterial;
import com.momomo.model.Skill;
import com.momomo.model.User;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
public class LearningMaterialManPage extends VerticalLayout {

    private ListSelect learningMaterialList;
    private LearningMaterialRepositoryInterface materialRepo;

    public LearningMaterialManPage(LearningMaterialRepositoryInterface learningMaterialRepositoryInterface) {
        this.materialRepo = learningMaterialRepositoryInterface;

        HorizontalLayout materialLayout = new HorizontalLayout();

        Label materialTitle = new Label("Learning Material Management System");
        materialTitle.addStyleName("h1");

        this.addComponent(materialTitle);
        this.addComponent(materialLayout);

        this.setWidth(100, Unit.PERCENTAGE);
        materialLayout.setWidth(100, Unit.PERCENTAGE);

        Panel learningMaterialBtnPanel = new Panel();
        FormLayout learningMaterialBtnLayout = new FormLayout();

        Button addMaterialBtn = new Button("Add Learning Material");
        Button removeMaterialBtn = new Button("Remove Learning Material");

        learningMaterialList = new ListSelect();
        learningMaterialList.setWidth(50, Unit.PERCENTAGE);
        learningMaterialList.setNullSelectionAllowed(false);
        learningMaterialList.setMultiSelect(false);
        updateLearningMaterialsList(this.materialRepo.getAllLearningMaterials());

        addMaterialBtn.addClickListener(new AddLearningMaterialPopupEventListener(this));
        removeMaterialBtn.addClickListener(new RemoveLearningMaterialEventListener(this.materialRepo, this, learningMaterialList));

        learningMaterialBtnPanel.setContent(learningMaterialBtnLayout);
        learningMaterialBtnLayout.addComponent(addMaterialBtn);
        learningMaterialBtnLayout.addComponent(removeMaterialBtn);
        materialLayout.addComponent(learningMaterialBtnLayout);
        materialLayout.addComponent(learningMaterialList);

        materialLayout.setExpandRatio(learningMaterialBtnLayout, 0.2f);
        materialLayout.setExpandRatio(learningMaterialList, 0.8f);


    }


    public void displayAddLearningMaterialPopup() {

        VerticalLayout popupContent = new VerticalLayout();
        TextField nameField = new TextField("Name");

        popupContent.addComponent(nameField);
        Button btn = new Button("Submit");
        popupContent.addComponent(btn);
        popupContent.setVisible(true);

        Window w = new Window();
        w.setContent(popupContent);
        btn.addClickListener(new AddLearningMaterialEventListener(this.materialRepo, this, nameField, w));
        this.getUI().addWindow(w);
    }

    public void updateLearningMaterialsList(List<LearningMaterial> materials) {

        learningMaterialList.removeAllItems();

        for (LearningMaterial material : materials) {
            learningMaterialList.addItem(material.getName());
        }

    }


}
