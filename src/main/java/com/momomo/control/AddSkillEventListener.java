package com.momomo.control;

import com.momomo.model.Skill;
import com.momomo.view.SkillManPage;
import com.momomo.view.UserManPage;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * Created by Monica on 2017-03-06.
 */
public class AddSkillEventListener implements Button.ClickListener{

    private final SkillRepositoryInterface skillRepo;
    private final SkillManPage page;
    private TextField nameField;
    private Window popup;

    //Pass in text fields to get values at moment of click
    public AddSkillEventListener(SkillRepositoryInterface skillRepo, SkillManPage page, TextField nameField, Window popup) {
        this.skillRepo = skillRepo;
        this.page = page;
        this.nameField = nameField;
        this.popup = popup;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {

        if(!skillRepo.addSkill(new Skill(nameField.getValue()))){
            Notification.show("User Already Exists","The Username you entered is already taken", Notification.Type.ERROR_MESSAGE);
        }

        page.updateSkillList(skillRepo.getAllSkills());
        page.getUI().removeWindow(popup);
    }
}
