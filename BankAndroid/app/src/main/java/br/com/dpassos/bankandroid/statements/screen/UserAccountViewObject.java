package br.com.dpassos.bankandroid.statements.screen;

import java.text.NumberFormat;

import br.com.dpassos.bankandroid.login.business.UserAccount;

class UserAccountViewObject{
    String statementUser;
    String statementAccount;
    String statementMoney;
    public UserAccountViewObject(UserAccount userAccount) {
        statementUser = userAccount.name;
        statementAccount = userAccount.bankAccount.concat(" / ").concat(userAccount.agency);
        statementMoney = NumberFormat.getCurrencyInstance().format(userAccount.balance);
    }
}
