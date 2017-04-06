package com.momomo.control;

import com.vaadin.data.validator.AbstractValidator;

/**
 * Created by Monica on 2017-03-16.
 */
public class PasswordValidator extends AbstractValidator<String> {

    public PasswordValidator(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Check if the specified password is a valid password
     * @param value The password getting validated
     * @return True if the given password is valid
     */
    @Override
    protected boolean isValidValue(String value) {
        //Password must have a capital letter, a lowercase letter, a number, and be between 8 and 15 characters

        return value != null && !(value.length() > 15 || value.length() < 8 || !value.matches(".*\\d.*") || !hasUpperCase(value) || !hasLowerCase(value));

    }

    /**
     * Get the type of the objects being validated
     * @return The type of the object being validated
     */
    @Override
    public Class<String> getType() {
        return String.class;
    }

    private boolean hasUpperCase(String value) {
        for (char c : value.toCharArray())
            if (Character.isUpperCase(c))
                return true;
        return false;
    }

    /**
     * Check if a string has any lowercase
     * @param value The string getting checked for lowercase
     * @return If the specified string has a lowercase letter
     */
    private boolean hasLowerCase(String value) {
        for (char c : value.toCharArray())
            if (Character.isLowerCase(c))
                return true;
        return false;
    }
}
