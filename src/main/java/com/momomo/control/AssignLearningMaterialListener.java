package com.momomo.control;

import com.momomo.model.LearningMaterial;
import com.momomo.model.User;
import com.vaadin.ui.Button;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by danielsauve on 2017-03-08.
 */
public class AssignLearningMaterialListener implements Button.ClickListener {
    private final ListSelect userList;
    private final ListSelect materialList;
    private final LearningMaterialRepositoryInterface materialRepo;
    private final UserRepositoryInterface userRepo;

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

        ArrayList<String> alreadyAddedMaterials = new ArrayList<>();
        ArrayList<String> addedMaterials = new ArrayList<>();
        boolean alreadyAdded = false;

        for (LearningMaterial material : learningMaterials) {
            if(user.getLearningMaterials().contains(material)) {
                alreadyAddedMaterials.add(material.getName());
                alreadyAdded = true;
            }
            else {
                user.addLearningMaterial(material);
                addedMaterials.add(material.getName());
            }
        }

        String notify = "";

        if(alreadyAdded) {
            notify = notify + "The user has already learned the following materials: " + String.join(",", alreadyAddedMaterials) + "\n";
        }

        if(addedMaterials.size() > 0) notify = notify + "User has now learned the following materials: " + String.join(",", addedMaterials);
        Notification.show(notify, Notification.Type.WARNING_MESSAGE);

        userRepo.updateUser(user);

    }
}
