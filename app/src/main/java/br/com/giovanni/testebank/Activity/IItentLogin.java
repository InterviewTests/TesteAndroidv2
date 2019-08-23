package br.com.giovanni.testebank.Activity;

import br.com.giovanni.testebank.Model.UserAccount;

public interface IItentLogin {
    void changeScreen(UserAccount userAccount);
    void setMessage (String setMessageString);
}