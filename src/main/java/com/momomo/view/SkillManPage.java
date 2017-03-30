package com.momomo.view;

import com.momomo.control.*;
import com.momomo.model.LearningMaterial;
import com.momomo.model.Skill;
import com.momomo.model.User;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charberg on 3/5/2017.
 */
public class SkillManPage extends VerticalLayout{

    private ListSelect userList;
    private final ListSelect skillList;
    private final UserRepositoryInterface userRepo;
    private final SkillRepositoryInterface skillRepo;
    private final LearningMaterialRepositoryInterface materialRepo;

    public SkillManPage(UserRepositoryInterface userRepo, SkillRepositoryInterface skillRepositoryInterface, LearningMaterialRepositoryInterface materialRepo) {

        this.userRepo = userRepo;
        this.skillRepo = skillRepositoryInterface;
        this.materialRepo = materialRepo;

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

    private void displayInspectSkillPopup() {

        if(skillList.getValue() == null) {
            return;
        }

        VerticalLayout popupContent = new VerticalLayout();

        TextArea userTextArea = new TextArea("Users with this skill:");
        TextArea materialTextArea = new TextArea("Learning Materials associated to this skill:");

        StringBuilder materialString = new StringBuilder();
        Skill skill = skillRepo.getSkillByName((String)skillList.getValue());
        List<LearningMaterial> learningMaterials = materialRepo.getAllLearningMaterials();

        for(LearningMaterial material : learningMaterials) {
            for (Skill s : material.getSkillList()){
                if (s.getName().equals(skill.getName())){
                    materialString.append(material.getName()).append("\n");
                }
            }
        }

        StringBuilder userString = new StringBuilder();

        ArrayList<User> addedUsers = new ArrayList<>();
        for(LearningMaterial material: learningMaterials) {
            for (Skill s : material.getSkillList()){
                if(s.getName().equals(skill.getName())) {
                    for (User user : userRepo.getUsersByLearningMaterial(material)) {
                        if (!addedUsers.contains(user)) {
                            addedUsers.add(user);
                            userString.append(user.getUsername()).append("\n");
                        }
                    }
                    break;
                }
            }
        }

        userTextArea.setValue(userString.toString());
        materialTextArea.setValue(materialString.toString());

        //Setting read only must be done AFTER setting text are value
        userTextArea.setReadOnly(true);
        materialTextArea.setReadOnly(true);
        popupContent.addComponent(new Label("Skill Name: " + skillList.getValue()));
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
