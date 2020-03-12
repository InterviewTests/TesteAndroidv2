package com.test.banktest.loginScreen;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginWorkerUnitTest {

    @Test
    public void verify_user_validation_IsCorrect(){
        LoginWorker loginWorker = new LoginWorker();
        Assert.assertTrue(loginWorker.validateUser("a@a.com")); // valid
        Assert.assertTrue(loginWorker.validateUser("73851981049")); // valid
        Assert.assertFalse(loginWorker.validateUser("701234")); // invalid
        Assert.assertFalse(loginWorker.validateUser("aaa")); // invalid
    }

    @Test
    public void verify_password_validation_IsCorrect(){
        LoginWorker loginWorker = new LoginWorker();
        Assert.assertTrue(loginWorker.validatePassword("@A9hfhfd")); // valid
        Assert.assertTrue(loginWorker.validatePassword("123@A5")); // valid
        Assert.assertFalse(loginWorker.validatePassword("1234")); // invalid
        Assert.assertFalse(loginWorker.validatePassword("aaa")); // invalid
    }
}
