package com.test.banktest.loginScreen;

import com.test.banktest.model.UserModel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

@RunWith(RobolectricTestRunner.class)
public class LoginPresenterUnitTest {

    @Test
    public void verify_LoginPresenter_configure_validation_error_messages_correctly(){
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.isUserValid = false;
        loginResponse.isPasswordValid = false;

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        loginPresenter.presentLoginData(loginResponse);

        //Then
        String ExpectedUserErrorText = "Usuário inválido. Informe o seu CPF ou e-mail";
        String ExpectedPasswordErrorText = "Senha inválida. Informe uma senha que tenha pelo menos uma letra maiuscula, um caracter especial e um caracter alfanumérico";

        Assert.assertEquals("When user is NOT valid, the message '"+ExpectedUserErrorText+"' should be returned",ExpectedUserErrorText,loginActivityInputSpy.viewModelCopy.userError);
        Assert.assertEquals("When user is NOT valid, the message '"+ExpectedPasswordErrorText+"' should be returned",ExpectedPasswordErrorText,loginActivityInputSpy.viewModelCopy.passwordError);
    }

    class LoginActivityInputSpy implements LoginActivityInput {

        LoginViewModel viewModelCopy;

        @Override
        public void displayLastUser(LoginViewModel viewModel) {

        }

        @Override
        public void displayLoginData(LoginViewModel viewModel) {
            viewModelCopy = viewModel;
        }
    }
}
