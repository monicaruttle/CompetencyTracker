package com.momomo.control;

import com.vaadin.data.validator.AbstractValidator;

/**
 * Created by Monica on 2017-03-22.
 */
public class UsernameValidator extends AbstractValidator<String>{

    public UsernameValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String s) {
        if (s.length() >= 5)
            return true;
        else return false;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
