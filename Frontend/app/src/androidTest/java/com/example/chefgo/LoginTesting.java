package com.example.chefgo;

import androidx.test.runner.AndroidJUnit4;

import com.example.chefgo.LoginorRegistrationActivity.Validation.ValidationRegister;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author SB_3
 *
 */

@RunWith(AndroidJUnit4.class)
public class LoginTesting {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(ValidationRegister.validateEmail("name@email.com"));
    }
    @Test
    public void emailValidator_IncorrectEmailSimple_ReturnsFalse() {
        assertFalse(ValidationRegister.validateEmail("name"));
    }
    @Test
    public void emailValidator_empty_returnsFalse() {
        assertFalse(ValidationRegister.validateEmail(""));
    }
    @Test
    public void passwordValidator_dontMatch_returnFalse(){
        assertFalse(ValidationRegister.validatePassword("pass","passs"));
    }
    @Test
    public void passwordValidator_Match_returnTrue(){
        assertTrue(ValidationRegister.validatePassword("pass","pass"));
    }
    @Test
    public void passwordValidator_null_returnFalse(){
        assertFalse(ValidationRegister.validatePassword(null,"pass"));


}

}
