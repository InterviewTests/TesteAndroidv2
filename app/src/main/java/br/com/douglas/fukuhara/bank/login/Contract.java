package br.com.douglas.fukuhara.bank.login;

import android.content.Intent;

import br.com.douglas.fukuhara.bank.network.vo.UserAccount;

public interface Contract {
    interface LoginActivityInput {
        void notifyResourceErrorToUser(int stringRes);
        void notifyErrorToUser(String message);
        void onSuccessfulLogin(UserAccount userAccount);
        void setLoginField(String lastSavedUser);
    }

    interface LoginRouterInput {
        void passDataToNextScene(UserAccount userAccount, Intent intent);
        Intent determineNextScreen();
    }

    interface LoginInteractorInput {
        void onLogin(String username, String password);
        void disposeAll();
        void checkForPreviousLoggedUser();
    }

    interface LoginPresenterInput {
        void emptyUsername();
        void emptyPassword();
        void invalidCpf();
        void invalidEmailCpf();
        void invalidPasswordType();
        void showLoginErrorMessage(String formatLoginErrorMsg);
        void showLoginGenericError();
        void onSuccessfulLoginResponse(UserAccount userAccount);
        void setLoginFromPreviousLoggedUser(String lastSavedUser);
    }
}
