package com.accenture.project.apptesteandroid.login;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

/**
 * Classe responsável por apresentar os dados para a LoginActivity
 */

interface ILoginPresenter {

    void presentMessage(String message);

    void presentLastUser(String login);

    void resetPasswordField();

    String formatAccountNumber(String accountNumber);

    String formatBalance(String originalBalance);
}

public class LoginPresenter implements ILoginPresenter {

    public WeakReference<ILoginActivity> iLoginActivity;


    @Override
    public void presentMessage(String message) {

        iLoginActivity.get().displayMessageToUser(message);
    }

    @Override
    public void presentLastUser(String login) {
        iLoginActivity.get().displayLastUserLogged(login);
    }

    @Override
    public String formatAccountNumber(String accountNumber) {
        //formata os números brutos da conta do usuário na forma correta para ser exibido
        if(accountNumber != null){
            StringBuilder stringBuilder = new StringBuilder(accountNumber);
            stringBuilder.insert(2, '.');
            stringBuilder.insert(accountNumber.length(), '-');
            return stringBuilder.toString();
        } else {
            return null;
        }

    }

    @Override
    public String formatBalance(String originalBalance) {
        //formata o valor do saldo do usuário na forma correta para ser exibido
        DecimalFormat df = new DecimalFormat("#,###.00");
        Log.d("balance", "formatBalance: " + originalBalance);
        if(originalBalance != null) {

            double balance = Double.parseDouble(originalBalance);
            return df.format(balance);
        } else {

            return null;
        }

    }

    @Override
    public void resetPasswordField() {
        iLoginActivity.get().resetPasswordField();
    }
}
