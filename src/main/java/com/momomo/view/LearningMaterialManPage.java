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

    private final ListSelect learningMaterialList;
    private final UserRepositoryInterface userRepo;
    private final LearningMaterialRepositoryInterface materialRepo;

    public LearningMaterialManPage(UserRepositoryInterface userRepo, SkillRepositoryInterface skillRepo, LearningMaterialRepositoryInterface learningMaterialRepositoryInterface) {

        this.userRepo = userRepo;
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
        Button inspectMaterialBtn = new Button("Inspect Learning Material");

        learningMaterialList = new ListSelect();
        learningMaterialList.setWidth(50, Unit.PERCENTAGE);
        learningMaterialList.setNullSelectionAllowed(false);
        learningMaterialList.setMultiSelect(false);
        updateLearningMaterialsList(this.materialRepo.getAllLearningMaterials());

        addMaterialBtn.addClickListener(new AddLearningMaterialPopupEventListener(this));
        removeMaterialBtn.addClickListener(new RemoveLearningMaterialEventListener(this.materialRepo, this, learningMaterialList));
        inspectMaterialBtn.addClickListener(e -> this.displayInspectLearningMaterialPopup());


        learningMaterialBtnPanel.setContent(learningMaterialBtnLayout);
        learningMaterialBtnLayout.addComponent(addMaterialBtn);
        learningMaterialBtnLayout.addComponent(removeMaterialBtn);
        learningMaterialBtnLayout.addComponent(inspectMaterialBtn);
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

        nameField.addFocusListener(new EnterListener(btn));

        Window w = new Window();
        w.setContent(popupContent);
        btn.addClickListener(new AddLearningMaterialEventListener(this.materialRepo, this, nameField, w));
        this.getUI().addWindow(w);
    }

    private void displayInspectLearningMaterialPopup() {

        if(learningMaterialList.getValue() == null) {
            return;
        }

        VerticalLayout popupContent = new VerticalLayout();

        TextArea skillTextArea = new TextArea("Skills with this material:");
        TextArea userTextArea = new TextArea("Users that have learned this material:");

        //Populate skill and user text areas, associated to selected material

        StringBuilder userString = new StringBuilder();

        for(User user : userRepo.getUsersByLearningMaterial(materialRepo.getLearningMaterialByName((String)learningMaterialList.getValue()))) {
            userString.append(user.getUsername()).append("\n");
        }

        StringBuilder skillString = new StringBuilder();

        for(Skill skill : materialRepo.getLearningMaterialByName((String)learningMaterialList.getValue()).getSkillList()) {
            skillString.append(skill.getName()).append("\n");
        }

        userTextArea.setValue(userString.toString());
        skillTextArea.setValue(skillString.toString());

        //Setting to read only must be done AFTER setting value!
        skillTextArea.setReadOnly(true);
        userTextArea.setReadOnly(true);
        popupContent.addComponent(new Label("Learning Material: " + learningMaterialList.getValue()));
        popupContent.addComponent(skillTextArea);
        popupContent.addComponent(userTextArea);
        Button btn = new Button("OK");
        popupContent.addComponent(btn);
        popupContent.setVisible(true);

        Window w = new Window();

        w.setContent(popupContent);
        btn.addClickListener(e -> this.getUI().removeWindow(w));
        this.getUI().addWindow(w);
    }


    public void updateLearningMaterialsList(List<LearningMaterial> materials) {

        learningMaterialList.removeAllItems();

        for (LearningMaterial material : materials) {
            learningMaterialList.addItem(material.getName());
        }

    }


}
