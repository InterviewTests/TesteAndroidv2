package br.com.douglas.fukuhara.bank.home.router;

import android.content.DialogInterface;
import android.content.Intent;

import java.lang.ref.WeakReference;

import br.com.douglas.fukuhara.bank.home.Contract;
import br.com.douglas.fukuhara.bank.home.ui.HomeActivity;
import br.com.douglas.fukuhara.bank.login.ui.LoginActivity;

public class HomeRouter implements Contract.HomeRouter {

    private WeakReference<HomeActivity> mHomeActivity;

    public void setActivity(WeakReference<HomeActivity> homeActivity) {
        mHomeActivity = homeActivity;
    }

    @Override
    public void onLogoutConfirmation(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(mHomeActivity.get(), LoginActivity.class);
        mHomeActivity.get().startActivity(intent);
        mHomeActivity.get().finish();
    }
}
