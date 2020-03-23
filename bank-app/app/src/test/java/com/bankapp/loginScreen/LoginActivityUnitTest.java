package com.bankapp.loginScreen;


import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.bankapp.ErrorResponse;
import com.bankapp.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.KITKAT)
public class LoginActivityUnitTest {

    LoginActivity activity;

    @Before
    public void setUp(){
        activity = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test
    public void LoginActivity_ShouldNOT_be_Null(){
        assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_getSavedUser() {
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        activity.output = loginActivityOutputSpy;

        activity.getSavedUser();

        assertTrue(loginActivityOutputSpy.getSavedUserIsCalled);
    }

    @Test
    public void onCreate_shouldCall_DisableFields() {
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        activity.output = loginActivityOutputSpy;

        //Fields
        AppCompatButton btnLogin = activity.findViewById( R.id.btnLogin);
        AppCompatEditText textInputUser = activity.findViewById(R.id.textInputUser);
        AppCompatEditText textInputPass = activity.findViewById(R.id.textInputPass);

        activity.getSavedUser();

        assertTrue(!btnLogin.isEnabled() && !textInputUser.isEnabled() && !textInputPass.isEnabled());
    }

    @Test
    public void true_Loading() {
        AppCompatButton btnLogin = activity.findViewById( R.id.btnLogin);
        AppCompatEditText textInputUser = activity.findViewById(R.id.textInputUser);
        AppCompatEditText textInputPass = activity.findViewById(R.id.textInputPass);

        activity.setLoading(true);

        assertTrue(!btnLogin.isEnabled()
                && !textInputUser.isEnabled()
                && !textInputPass.isEnabled()
                && (btnLogin.getText().equals("Carregando...")));
    }

    @Test
    public void false_Loading() {
        AppCompatButton btnLogin = activity.findViewById( R.id.btnLogin);
        AppCompatEditText textInputUser = activity.findViewById(R.id.textInputUser);
        AppCompatEditText textInputPass = activity.findViewById(R.id.textInputPass);

        activity.setLoading(false);

        assertTrue(btnLogin.isEnabled()
                && textInputUser.isEnabled()
                && textInputPass.isEnabled()
                && (btnLogin.getText().equals("Login")));
    }

    @Test
    public void signIn_shouldCall_Loading() {
        AppCompatButton btnLogin = activity.findViewById( R.id.btnLogin);
        AppCompatEditText textInputUser = activity.findViewById(R.id.textInputUser);
        AppCompatEditText textInputPass = activity.findViewById(R.id.textInputPass);

        activity.signIn(new LoginViewModel());

        assertTrue(btnLogin.isEnabled()
                && textInputUser.isEnabled()
                && textInputPass.isEnabled()
                && (btnLogin.getText().equals("Login")));
    }

    @Test
    public void signIn_shouldCall_passDataToNextScene() {
        LoginRouterOutputSpy loginRouterOutputSpy = new LoginRouterOutputSpy();
        activity.router = loginRouterOutputSpy;

        activity.signIn(new LoginViewModel());

        assertTrue(loginRouterOutputSpy.passDataToNextSceneIsCalled);
    }

    @Test
    public void errorSignIn_shouldCall_Loading() {
        AppCompatButton btnLogin = activity.findViewById( R.id.btnLogin);
        AppCompatEditText textInputUser = activity.findViewById(R.id.textInputUser);
        AppCompatEditText textInputPass = activity.findViewById(R.id.textInputPass);

        LoginViewModel view = new LoginViewModel();
        view.error = new ErrorResponse("Teste erro");

        activity.errorSignIn(view);

        assertTrue(btnLogin.isEnabled()
                && textInputUser.isEnabled()
                && textInputPass.isEnabled()
                && (btnLogin.getText().equals("Login"))
                && ShadowToast.showedToast(view.error.toString()));
    }

    @Test
    public void btnLoginClick_shouldCall_doLogin() {
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        activity.output = loginActivityOutputSpy;

        //Fields
        AppCompatButton btnLogin = activity.findViewById( R.id.btnLogin);
        AppCompatEditText textInputUser = activity.findViewById(R.id.textInputUser);
        AppCompatEditText textInputPass = activity.findViewById(R.id.textInputPass);

        //Bind
        textInputUser.setText("User");
        textInputPass.setText("Pass");

        btnLogin.performClick();

        assertTrue(loginActivityOutputSpy.doLoginIsCalled);
    }

    @Test
    public void errorUserOrPasswordInvalid_with_invalid_user() {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.wrongUser = true;
        loginViewModel.wrongPassword = false;

        //Fields
        AppCompatEditText textInputUser = activity.findViewById(R.id.textInputUser);

        activity.errorUserOrPasswordInvalid(loginViewModel);

        assertTrue(textInputUser.getError().equals("Digite um email ou cpf válido para fazer login."));
    }

    @Test
    public void errorUserOrPasswordInvalid_with_invalid_password() {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.wrongPassword = true;
        loginViewModel.wrongUser = false;

        //Fields
        AppCompatEditText textInputPass = activity.findViewById(R.id.textInputPass);

        activity.errorUserOrPasswordInvalid(loginViewModel);

        assertTrue(textInputPass.getError().equals("Sua senha deve conter pelo menos uma letra maiúscula, um caracter especial e um caracter alfanumérico."));
    }

    @Test
    public void btnLoginClick_shouldCall_checkFieldUser() {
        AppCompatButton btnLogin = activity.findViewById( R.id.btnLogin);
        AppCompatEditText textInputUser = activity.findViewById(R.id.textInputUser);

        btnLogin.performClick();

        assertEquals("Campo obrigatório", textInputUser.getError().toString());
    }

    @Test
    public void btnLoginClick_shouldCall_checkFieldPass() {
        AppCompatButton btnLogin = activity.findViewById( R.id.btnLogin);
        AppCompatEditText textInputUser = activity.findViewById(R.id.textInputUser);
        AppCompatEditText textInputPass = activity.findViewById(R.id.textInputPass);

        //Bind user
        textInputUser.setText("User");

        btnLogin.performClick();

        assertEquals("Campo obrigatório", textInputPass.getError().toString());
    }

    private class LoginActivityOutputSpy implements LoginInteractorInput {

        boolean getSavedUserIsCalled = false;
        boolean doLoginIsCalled = false;

        @Override
        public void doLogin(Context context, LoginRequest request) {
            doLoginIsCalled = true;
        }

        @Override
        public void getSavedUser(Context context) {
            getSavedUserIsCalled = true;
        }
    }

    private class LoginRouterOutputSpy implements LoginRouterInput {

        boolean passDataToNextSceneIsCalled = false;

        @Override
        public void passDataToNextScene() {
            passDataToNextSceneIsCalled = true;
        }

        @Override
        public Intent determineNextScreen() {
            return null;
        }
    }
}

