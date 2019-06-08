package br.com.douglas.fukuhara.bank.login;

public interface Contract {
    interface LoginActivityInput {
        void notifyErrorToUser(int stringRes);
    }

    interface LoginRouterInput {

    }

    interface LoginInteractorInput {
        void onLogin(String username, String password);
    }

    interface LoginPresenterInput {
        void emptyUsername();
        void emptyPassword();
        void invalidCpf();
        void invalidEmailCpf();
        void invalidPasswordType();
    }
}
