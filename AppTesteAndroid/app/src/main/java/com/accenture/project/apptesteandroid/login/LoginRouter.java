package com.accenture.project.apptesteandroid.login;

import android.content.Intent;

import com.accenture.project.apptesteandroid.bankStatement.BankStatementActivity;
import com.accenture.project.apptesteandroid.model.UserAccount;

import java.lang.ref.WeakReference;

/**
 * Classe responsável por chamar BankStatementActivity a partir de LoginActivity
 * enviando os dados da conta do usuário via putExtra
 */

interface ILoginRouter{
    void callNextActivity(UserAccount userAccount);
}
public class LoginRouter implements ILoginRouter{

    public WeakReference<LoginActivity> loginActivity;


    @Override
    public void callNextActivity(UserAccount userAccount) {
        Intent intent = new Intent(loginActivity.get(), BankStatementActivity.class);
        intent.putExtra("userAccount", userAccount);
        loginActivity.get().startActivity(intent);
    }


}
