package com.momomo.control;

import com.vaadin.data.validator.AbstractValidator;

/**
 * Created by Monica on 2017-03-22.
 */
public class UsernameValidator extends AbstractValidator<String>{

    public UsernameValidator(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Check if the specified username is a valid username
     * @param s The username getting validated
     * @return True if the given username is valid
     */
    @Override
    protected boolean isValidValue(String s) {
        return s.length() >= 5;
    }

    /**
     * Get the type of the objects being validated
     * @return The type of the object being validated
     */
    @Override
    public Class<String> getType() {
        return String.class;
    }
}
