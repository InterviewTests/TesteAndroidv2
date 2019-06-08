package br.com.douglas.fukuhara.bank.login.interactor;

import br.com.douglas.fukuhara.bank.login.Contract;
import br.com.douglas.fukuhara.bank.login.presenter.LoginPresenter;
import br.com.douglas.fukuhara.bank.utils.UsernameValidation;

import static br.com.douglas.fukuhara.bank.utils.LoginUtils.isValidPasswordPattern;
import static br.com.douglas.fukuhara.bank.utils.LoginUtils.isValidUsernamePattern;

public class LoginInteractor implements Contract.LoginInteractorInput {

    private Contract.LoginPresenterInput mPresenter;

    public void setPresenter(LoginPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLogin(String username, String password) {
        if (username.isEmpty()) {
            mPresenter.emptyUsername();
            return;
        }

        if (password.isEmpty()) {
            mPresenter.emptyPassword();
            return;
        }

        @UsernameValidation.Type int validUsername = isValidUsernamePattern(username);
        switch (validUsername) {
            case UsernameValidation.INVALID_CPF:
                mPresenter.invalidCpf();
                return;
            case UsernameValidation.INVALID_EMAIL_CPF:
                mPresenter.invalidEmailCpf();
                return;
            case UsernameValidation.VALID_CPF:
            case UsernameValidation.VALID_EMAIL:
                // If the informed username matches either in CPF or in Email pattern, lets
                // proceed in validating the password pattern
                break;
        }

        if (!isValidPasswordPattern(password)) {
            mPresenter.invalidPasswordType();
            return;
        }

        // TODO : Perform Authentication via WebServer
    }
}
