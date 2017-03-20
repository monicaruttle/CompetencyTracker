package com.momomo.control;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Monica on 2017-03-16.
 */
public class PasswordValidatorTest {
    @Test
    public void isValidValue() throws Exception {
        PasswordValidator validator = new PasswordValidator("Invalid");

        //missing uppercase and number
        String val1 = "momomomo";
        assertFalse(validator.isValidValue(val1));

        //missing lowercase and number
        String val2 = "MOMOMOMO";
        assertFalse(validator.isValidValue(val2));

        //missing an uppercase
        String val3 = "momomomo3";
        assertFalse(validator.isValidValue(val3));

        //missing a lowercase
        String val4 = "MOMOMOMO3";
        assertFalse(validator.isValidValue(val4));

        //too short
        String val5 = "mO3";
        assertFalse(validator.isValidValue(val5));

        //too long
        String val6 = "momomomomomomomomomomomomomomoM0";
        assertFalse(validator.isValidValue(val6));

        //just right
        String val7 = "mOmOmOmO5";
        assertTrue(validator.isValidValue(val7));
    }

    @Test
    public void getType() throws Exception {

    }

}