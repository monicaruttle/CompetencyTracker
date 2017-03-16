package com.momomo.control;

import com.momomo.view.SkillManPage;
import com.momomo.view.UserManPage;
import com.vaadin.ui.Button;
import com.vaadin.ui.ListSelect;

/**
 * Created by Monica on 2017-03-06.
 */
public class RemoveSkillEventListener implements Button.ClickListener {
    private final SkillRepositoryInterface skillRepo;
    private final SkillManPage page;
    private ListSelect list;

    //Pass in text fields to get values at moment of click
    public RemoveSkillEventListener(SkillRepositoryInterface skillRepo, SkillManPage page, ListSelect list) {
        this.skillRepo = skillRepo;
        this.page = page;
        this.list = list;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {
        String val = (String)list.getValue();
        skillRepo.removeSkill((String)list.getValue());
        page.updateSkillList(skillRepo.getAllSkills());
    }
}
