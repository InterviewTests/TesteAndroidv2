package com.example.jcsantos.santanderteste;

import android.support.test.runner.AndroidJUnit4;

import com.example.jcsantos.santanderteste.Components.Utils.ItemsValidate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginValidateTest {

    @Test
    public void validateCpf() {
        ItemsValidate validate = new ItemsValidate();
        boolean cpfOk = validate.isValid("57643945896");
        Assert.assertTrue(cpfOk);
    }

    @Test
    public void validateEmail() {
        ItemsValidate validate = new ItemsValidate();
        boolean emailOk = validate.isValid("teste@hotmail.com");
        Assert.assertTrue(emailOk);
    }

    @Test
    public void validatePassword() {
        ItemsValidate validate = new ItemsValidate();
        boolean cpfOk = validate.checkPassword("Text@1");
        Assert.assertTrue(cpfOk);
    }
}
