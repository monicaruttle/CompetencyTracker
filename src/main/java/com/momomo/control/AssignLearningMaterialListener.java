package com.momomo.control;

import com.momomo.model.LearningMaterial;
import com.momomo.model.User;
import com.vaadin.ui.Button;
import com.vaadin.ui.ListSelect;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by danielsauve on 2017-03-08.
 */
public class AssignLearningMaterialListener implements Button.ClickListener {
    private ListSelect userList;
    private ListSelect materialList;
    private LearningMaterialRepositoryInterface materialRepo;
    private UserRepositoryInterface userRepo;

    public AssignLearningMaterialListener(ListSelect userList, ListSelect materialList, LearningMaterialRepositoryInterface materialRepo, UserRepositoryInterface userRepo) {
        this.userList = userList;
        this.materialList = materialList;
        this.materialRepo = materialRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String userName = (String) userList.getValue();
        User user = userRepo.getUserByUserName(userName);

        ArrayList<LearningMaterial> learningMaterials = new ArrayList<>();
        Collection<String> learningMaterialNames = (Collection) materialList.getValue();
        for (String s : learningMaterialNames) {
            learningMaterials.add(materialRepo.getLearningMaterialByName(s));
        }
        for (LearningMaterial material : learningMaterials) {
            user.addLearningMaterial(material);
        }
        userRepo.updateUser(user);
    }
}
