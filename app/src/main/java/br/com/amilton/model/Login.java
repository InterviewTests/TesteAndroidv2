package br.com.amilton.model;

import java.util.Map;

public class Login {
    private UserAccount userAccount;
    private Map<String, String> error;

    public static final Login EMPTY_LOGIN = new Login();

    private Login() {}

    public Login(UserAccount userAccount, Map<String, String> error) {
        this.userAccount = userAccount;
        this.error = error;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }
}
