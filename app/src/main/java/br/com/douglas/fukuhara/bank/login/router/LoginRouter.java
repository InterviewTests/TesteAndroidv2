package br.com.douglas.fukuhara.bank.login.router;

import java.lang.ref.WeakReference;

import br.com.douglas.fukuhara.bank.login.Contract;
import br.com.douglas.fukuhara.bank.login.ui.LoginActivity;

public class LoginRouter implements Contract.LoginRouterInput {

    private WeakReference<LoginActivity> mLoginActivity;

    public void setActivity(WeakReference<LoginActivity> loginActivity) {
        mLoginActivity = loginActivity;
    }
}
