package com.momomo.control;

import com.momomo.model.Role;
import com.momomo.view.MainPage;

/**
 * Created by Monica on 2017-03-22.
 */
final class PrivilegeManager {

    public static void SetVisibilities(Role role, MainPage mainPage) {
        switch (role){
            case ADMIN:
                SetAdminVisibility(mainPage);
                break;
            case ASSESSOR:
                SetAssessorVisibility(mainPage);
                break;
            case BASIC:
                SetBasicVisibility(mainPage);
        }
    }

    private static void SetBasicVisibility(MainPage mainPage) {
        mainPage.setMenuBarVisibility(false);
        mainPage.setUserCreationVisibility(false);
    }

    private static void SetAssessorVisibility(MainPage mainPage) {
        mainPage.setMenuBarVisibility(true);
        mainPage.setUserCreationVisibility(false);
    }

    private static void SetAdminVisibility(MainPage mainPage) {
        mainPage.setMenuBarVisibility(true);
        mainPage.setUserCreationVisibility(true);
    }
}
