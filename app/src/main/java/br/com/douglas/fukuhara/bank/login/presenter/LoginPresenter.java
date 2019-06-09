package br.com.douglas.fukuhara.bank.login.presenter;

import java.lang.ref.WeakReference;

import br.com.douglas.fukuhara.bank.R;
import br.com.douglas.fukuhara.bank.login.Contract;
import br.com.douglas.fukuhara.bank.network.vo.UserAccount;

public class LoginPresenter implements Contract.LoginPresenterInput {

    private WeakReference<Contract.LoginActivityInput> mLoginActivity;

    public void setOutput(WeakReference<Contract.LoginActivityInput> loginActivity) {
        mLoginActivity = loginActivity;
    }

    private Contract.LoginActivityInput getLoginActivity() {
        return mLoginActivity.get();
    }

    @Override
    public void emptyUsername() {
        if (getLoginActivity() != null) {
            getLoginActivity().notifyResourceErrorToUser(R.string.login_empty_username);
        }
    }

    @Override
    public void emptyPassword() {
        if (getLoginActivity() != null) {
            getLoginActivity().notifyResourceErrorToUser(R.string.login_empty_password);
        }
    }

    @Override
    public void invalidCpf() {
        if (getLoginActivity() != null) {
            getLoginActivity().notifyResourceErrorToUser(R.string.login_invalid_cpf);
        }
    }

    @Override
    public void invalidEmailCpf() {
        if (getLoginActivity() != null) {
            getLoginActivity().notifyResourceErrorToUser(R.string.login_invalid_email_cpf);
        }
    }

    @Override
    public void invalidPasswordType() {
        if (getLoginActivity() != null) {
            getLoginActivity().notifyResourceErrorToUser(R.string.login_invalid_password_pattern);
        }
    }

    @Override
    public void showLoginErrorMessage(String formatLoginErrorMsg) {
        if (getLoginActivity() != null) {
            getLoginActivity().notifyErrorToUser(formatLoginErrorMsg);
        }
    }

    @Override
    public void showLoginGenericError() {
        if (getLoginActivity() != null) {
            getLoginActivity().notifyResourceErrorToUser(R.string.login_generic_error);
        }
    }

    @Override
    public void onSuccessfulLoginResponse(UserAccount userAccount) {
        if (getLoginActivity() != null) {
            getLoginActivity().onSuccessfulLogin(userAccount);
        }
    }
}
