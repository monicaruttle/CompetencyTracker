package com.momomo.control;

import com.vaadin.data.validator.AbstractValidator;

/**
 * Created by Monica on 2017-03-16.
 */
public class PasswordValidator extends AbstractValidator<String> {

    public PasswordValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String value) {
        //Password must have a capital letter, a lowercase letter, a number, and be between 8 and 15 characters

        if (value == null)
            return false;

        if (value.length() > 15 ||
                value.length() < 8 ||
                !value.matches(".*\\d.*") ||
                !hasUpperCase(value) ||
                !hasLowerCase(value)) {
            return false;
        }
        return true;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    public boolean hasUpperCase(String value) {
        for (char c : value.toCharArray())
            if (Character.isUpperCase(c))
                return true;
        return false;
    }

    public boolean hasLowerCase(String value) {
        for (char c : value.toCharArray())
            if (Character.isLowerCase(c))
                return true;
        return false;
    }
}
