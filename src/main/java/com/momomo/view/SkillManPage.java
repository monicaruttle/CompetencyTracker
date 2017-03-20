package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.Skill;
import com.momomo.model.User;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
public class SkillManPage extends VerticalLayout{

    private ListSelect userList;
    private ListSelect skillList;
    private SkillRepositoryInterface skillRepo;

    public SkillManPage(SkillRepositoryInterface skillRepositoryInterface) {

        /////////////////////////////////////////////////////////////////////////
        this.skillRepo = skillRepositoryInterface;

        HorizontalLayout skillLayout = new HorizontalLayout();

        Label skillTitle = new Label( "Skill Modification System" );
        skillTitle.addStyleName( "h1" );

        this.addComponent(skillTitle);
        this.addComponent(skillLayout);


        skillLayout.setWidth(100, Unit.PERCENTAGE);

        Panel skillBtnPanel = new Panel();
        FormLayout skillBtnLayout = new FormLayout();

        Button addSkillBtn = new Button("Add Skill");
        Button removeSkillBtn = new Button("Remove Skill");
        Button inspectSkillBtn = new Button("Inspect Skill");

        skillList = new ListSelect();
        skillList.setWidth(50, Unit.PERCENTAGE);
        skillList.setNullSelectionAllowed(false);
        skillList.setMultiSelect(false);
        updateSkillList(skillRepo.getAllSkills());

        addSkillBtn.addClickListener(new AddSkillPopupEventListener(this));
        removeSkillBtn.addClickListener(new RemoveSkillEventListener(skillRepo, this, skillList));
        inspectSkillBtn.addClickListener(e -> this.displayInspectSkillPopup());

        skillBtnPanel.setContent(skillBtnLayout);
        skillBtnLayout.addComponent(addSkillBtn);
        skillBtnLayout.addComponent(removeSkillBtn);
        skillBtnLayout.addComponent(inspectSkillBtn);
        skillLayout.addComponent(skillBtnLayout);
        skillLayout.addComponent(skillList);

        skillLayout.setExpandRatio(skillBtnLayout, 0.2f);
        skillLayout.setExpandRatio(skillList, 0.8f);

    }


    public void displayAddSkillPopup() {

        VerticalLayout popupContent = new VerticalLayout();
        TextField nameField = new TextField("Name");

        popupContent.addComponent(nameField);
        Button btn = new Button("Submit");
        popupContent.addComponent(btn);
        popupContent.setVisible(true);

        Window w = new Window();
        w.setContent(popupContent);
        btn.addClickListener(new AddSkillEventListener(skillRepo, this, nameField, w));
        this.getUI().addWindow(w);
    }

    public void displayInspectSkillPopup() {

        if(skillList.getValue() == null) {
            return;
        }

        VerticalLayout popupContent = new VerticalLayout();

        TextArea userTextArea = new TextArea("Users with this skill:");
        TextArea materialTextArea = new TextArea("Learning Materials associated to this skill:");
        userTextArea.setReadOnly(true);
        materialTextArea.setReadOnly(true);

        //TODO: Add skill and user values to skillTextArea and userTextArea, which are associated with selected material

        popupContent.addComponent(new Label("Skill Name: " + (String)skillList.getValue()));
        popupContent.addComponent(userTextArea);
        popupContent.addComponent(materialTextArea);
        Button btn = new Button("OK");
        popupContent.addComponent(btn);
        popupContent.setVisible(true);

        Window w = new Window();

        w.setContent(popupContent);
        btn.addClickListener(e -> this.getUI().removeWindow(w));
        this.getUI().addWindow(w);
    }

    public void updateSkillList(List<Skill> skills) {

        skillList.removeAllItems();

        for(Skill skill: skills) {
            skillList.addItem(skill.getName());
        }

    }

}
