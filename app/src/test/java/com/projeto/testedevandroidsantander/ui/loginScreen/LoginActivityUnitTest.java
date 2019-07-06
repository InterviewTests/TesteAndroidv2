package com.projeto.testedevandroidsantander.ui.loginScreen;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {
    @Before
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void LoginActivity_ShouldNOT_be_Null(){
        //Given
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        //When

        // Then
        Assert.assertNotNull(activity);
    }

}
