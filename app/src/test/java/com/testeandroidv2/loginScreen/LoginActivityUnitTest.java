package com.testeandroidv2.loginScreen;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LoginActivityUnitTest {

    @Test
    public void loginActivity_ShouldNot_Null(){
        // Given
        LoginActivity activity = new LoginActivity();
        // When

        // Then
        Assert.assertNotNull(activity);
    }
}
