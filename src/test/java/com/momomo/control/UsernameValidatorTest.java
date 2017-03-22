package com.momomo.control;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Monica on 2017-03-22.
 */
public class UsernameValidatorTest {

    @Test
    public void isValidValue() throws Exception {
        UsernameValidator validator = new UsernameValidator("Invalid username");

        assertFalse(validator.isValidValue("a"));
        assertTrue(validator.isValidValue("aaaaaaaaaa"));
    }

}