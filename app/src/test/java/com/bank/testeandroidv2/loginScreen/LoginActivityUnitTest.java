package com.bank.testeandroidv2.loginScreen;

import android.widget.Button;
import android.widget.EditText;

import com.bank.testeandroidv2.R;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {
    private LoginActivity loginActivity;
    private EditText user;
    private EditText password;
    private Button button;

    private static final String STRING_EMPTY = "";

    @Before
    public void setUp(){
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        user = (EditText)loginActivity.findViewById(R.id.et_user);
        password = (EditText)loginActivity.findViewById(R.id.et_password);
        button = (Button) loginActivity.findViewById(R.id.btn_login);

    }
    @After
    public void tearDown(){}


    @Test
    public void LoginActivity_ShouldNOT_be_Null(){
        Assert.assertNotNull(loginActivity);
    }

    @Test
    public void onCreate_shouldCall_processFormOnClick(){
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();

        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchMetaData to test our condition
        loginActivity.output = loginActivityOutputSpy;

        //When
        button.performClick();

        //Then
        Assert.assertTrue(loginActivityOutputSpy.validateFormIsCalled);
    }

    @Test
    public void onCreate_Calls_processForm_withNotNullAndNotEmptyData(){
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        loginActivity.output = loginActivityOutputSpy;
        user.setText("paulo@bank.com");
        password.setText("1qQ!");

        //When
        button.performClick();

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.password);
        Assert.assertNotEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertNotEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.password);

        Assert.assertTrue(loginActivityOutputSpy.validateFormIsCalled);
    }

    @Test
    public void onCreate_Calls_processForm_withNotNullAndNotEmptyUserData(){
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();

        loginActivity.output = loginActivityOutputSpy;

        user.setText("paulo@bank.com");

        //When
        button.performClick();

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.password);
        Assert.assertNotEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.password);
    }

    @Test
    public void onCreate_Calls_processForm_withNotNullAndNotEmptyPasswordData(){
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();

        loginActivity.output = loginActivityOutputSpy;

        password.setText("1qQ!");

        //When
        button.performClick();

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.password);
        Assert.assertEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertNotEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.password);
    }

    @Test
    public void onCreate_Calls_processForm_withEmptyData(){
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();

        loginActivity.output = loginActivityOutputSpy;

        //When
        button.performClick();

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.password);
        Assert.assertEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.password);
    }

    @Test
    public void onCreate_Calls_submitForm_after_validate(){
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        loginActivity.output = loginActivityOutputSpy;
//        user.setText("paulo@bank.com");
//        password.setText("1qQ!");

        //When
        loginActivity.submitForm();

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.password);
        Assert.assertTrue(loginActivityOutputSpy.submitFormIsCalled);
        Assert.assertNotEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.user);
        Assert.assertNotEquals(STRING_EMPTY,loginActivityOutputSpy.loginRequestCopy.password);

    }

    private class LoginActivityOutputSpy implements LoginInteractorInput {

        boolean validateFormIsCalled = false;
        boolean submitFormIsCalled = false;
        LoginRequest loginRequestCopy;
        UserAccount userAcountCopy;

        @Override
        public void validateLoginData(LoginRequest request) {
            validateFormIsCalled = true;
            loginRequestCopy = request;
        }

        @Override
        public void requestLoginDataOnServer(LoginRequest request) {
            submitFormIsCalled = true;
            loginRequestCopy = request;
        }
    }


}
