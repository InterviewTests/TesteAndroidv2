package com.bank.testeandroidv2.loginScreen;

import com.bank.testeandroidv2.util.CPFUtil;
import com.bank.testeandroidv2.util.EmailUtil;
import com.bank.testeandroidv2.util.PasswordUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class LoginInteractorUnitTest {
    private static int EXPECTED_ENUM = 0;

    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void validateLoginData_with_validInput_shouldCall_processLoginDataForm(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;
        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertTrue("When the valid input is passed to LoginInteractor " +
                        "Then processLoginDataForm should be called",
                loginPresenterInputSpy.presentLoginDataValidationFormIsCalled);
    }

    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_FIELDS_OK_asEmail(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "paulo@bank.com";
        loginRequest.password = "1qQ!";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.FIELDS_OK.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }

    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_FIELDS_OK_asCPF(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "078.177.526-48";
        loginRequest.password = "1qQ!";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.FIELDS_OK.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }

    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_EMPTY_FIELDS(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.EMPTY_FIELDS.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }

    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_USER_FIELD_EMPTY(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.password = "1qQ!";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.USER_FIELD_EMPTY.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }
    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_PASS_FIELD_EMPTY(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "paulo@bank.com";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.PASS_FIELD_EMPTY.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }

    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_INVALID_USER_CPF(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "078.177.526.48";
        loginRequest.password = "1qQ!";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.INVALID_USER_CPF.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }

    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_INVALID_USER_EMAIL(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "paulo@bank@.com";
        loginRequest.password = "1qQ!";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.INVALID_USER_EMAIL.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }

    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_INVALID_PASSWORD_asSpecialCharacter(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "paulo@bank.com";
        loginRequest.password = "1qQ";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.INVALID_PASSWORD.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }

    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_INVALID_PASSWORD_asUppercase(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "paulo@bank.com";
        loginRequest.password = "1q!";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.INVALID_PASSWORD.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }

    @Test
    public void validateLoginData_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_INVALID_PASSWORD_asAlphanumeric(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "paulo@bank.com";
        loginRequest.password = "qQ!";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.INVALID_PASSWORD.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
    }

    @Test
    public void requestLoginDataOnServer_with_validInput_processLoginDataForm_shouldCall_worker_verifyTextFieldsAreCorrect_FIELDS_OK_asCPF(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "078.177.526-48";
        loginRequest.password = "1qQ!";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);
        EXPECTED_ENUM = LoginStatusMessageEnum.FIELDS_OK.getValue();

        //When
        loginInteractor.validateLoginData(loginRequest);
        loginInteractor.requestLoginDataOnServer(loginRequest);

        //Then
        Assert.assertEquals(EXPECTED_ENUM ,loginWorkerInputSpy.status);
        Assert.assertTrue(loginPresenterInputSpy.processRequestLoginDataOnServerIsCalled);
        Assert.assertNotNull(loginPresenterInputSpy.userAccountCopy);
    }

    private class LoginPresenterInputSpy implements LoginPresenterInput {

        boolean presentLoginDataValidationFormIsCalled = false;
        boolean processRequestLoginDataOnServerIsCalled = false;
        boolean processErrorRequestIsCalled = false;
        LoginResponse loginResponseCopy;
        UserAccount userAccountCopy;
        String errorCopy;

        @Override
        public void presentLoginDataValidationForm(LoginResponse response) {
            presentLoginDataValidationFormIsCalled = true;
            loginResponseCopy = response;
        }

        @Override
        public void processRequestLoginDataOnServer(UserAccount response) {
            processRequestLoginDataOnServerIsCalled = true;
            userAccountCopy = response;
        }

        @Override
        public void processErrorRequest(String error) {
            processErrorRequestIsCalled = true;
            errorCopy = error;
        }
    }

    private class LoginWorkerInputSpy implements LoginWorkerInput {
        public int status;

        @Override
        public int verifyTextFieldsAreCorrect(LoginRequest request) {
            if ((null == request.user || request.user.isEmpty())
                    && (null == request.password || request.password.isEmpty())) {
                //User empty and password empty
                status = LoginStatusMessageEnum.EMPTY_FIELDS.getValue();
            } else if (null == request.password || request.password.isEmpty()) {
                //Password empty
                status = LoginStatusMessageEnum.PASS_FIELD_EMPTY.getValue();
            } else if ((null == request.user || request.user.isEmpty())) {
                //User empty and password filled
                status = LoginStatusMessageEnum.USER_FIELD_EMPTY.getValue();
            } else {
                if (request.user.contains(("@"))) {
                    if (EmailUtil.isEmailValid(request.user)) {
                        if (PasswordUtil.isPasswordStrong(request.password)) {
                            status = LoginStatusMessageEnum.FIELDS_OK.getValue();
                        } else {
                            status = LoginStatusMessageEnum.INVALID_PASSWORD.getValue();
                        }
                    } else {
                        //User(email) invalid
                        status = LoginStatusMessageEnum.INVALID_USER_EMAIL.getValue();
                    }
                } else {
                    if (CPFUtil.isCPF(request.user)) {
                        if (PasswordUtil.isPasswordStrong(request.password)) {
                            status = LoginStatusMessageEnum.FIELDS_OK.getValue();
                        } else {
                            status = LoginStatusMessageEnum.INVALID_PASSWORD.getValue();
                        }
                    } else {
                        //User(CPF) invalid
                        status = LoginStatusMessageEnum.INVALID_USER_CPF.getValue();
                    }
                }
            }
            return status;
        }
    }
}