package br.com.douglas.fukuhara.bank.login.router;

import android.content.Intent;

import java.lang.ref.WeakReference;

import br.com.douglas.fukuhara.bank.home.ui.HomeActivity;
import br.com.douglas.fukuhara.bank.login.Contract;
import br.com.douglas.fukuhara.bank.login.ui.LoginActivity;
import br.com.douglas.fukuhara.bank.network.vo.UserAccount;

import static br.com.douglas.fukuhara.bank.utils.LoginUtils.serializeUserAccountObj;

public class LoginRouter implements Contract.LoginRouterInput {

    private WeakReference<LoginActivity> mLoginActivity;

    public void setActivity(WeakReference<LoginActivity> loginActivity) {
        mLoginActivity = loginActivity;
    }

    @Override
    public void passDataToNextScene(UserAccount userAccount, Intent intent) {
        serializeUserAccountObj(userAccount, intent);
    }

    @Override
    public Intent determineNextScreen() {
        return new Intent(mLoginActivity.get(), HomeActivity.class);
    }
}
