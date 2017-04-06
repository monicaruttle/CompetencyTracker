package com.momomo.control;

import com.momomo.model.Role;
import com.momomo.view.MainPage;

/**
 * Created by Monica on 2017-03-22.
 */
final class PrivilegeManager {

    /**
     * Set the visibilities on the main page based on the specified user role
     * @param role The current user's role
     * @param mainPage The main page of the application
     */
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

    /**
     * Sets the visibilities on the main page for a basic user
     * @param mainPage The main page of the application
     */
    private static void SetBasicVisibility(MainPage mainPage) {
        mainPage.setUserCreationVisibility(false);
        mainPage.setBasicVisilibility(false);
        mainPage.setMenuBarVisibility(true);
    }

    /**
     * Sets the visibilities on the main page for an assessor user
     * @param mainPage The main page of the application
     */
    private static void SetAssessorVisibility(MainPage mainPage) {
        mainPage.setUserCreationVisibility(false);
        mainPage.setMenuBarVisibility(true);
        mainPage.setBasicVisilibility(true);
    }

    /**
     * Sets the visibilities on the main page for an admin user
     * @param mainPage The main page of the application
     */
    private static void SetAdminVisibility(MainPage mainPage) {
        mainPage.setUserCreationVisibility(true);
        mainPage.setMenuBarVisibility(true);
        mainPage.setBasicVisilibility(true);
    }
}
