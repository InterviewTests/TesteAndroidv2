package br.com.accenture.santander.wallacebaldenebre.model;

public class User {
    private Error error;
    private UserAccount userAccount;

    public User(UserAccount userAccount, Error error){
        this.error = error;
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        error = error;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        userAccount = userAccount;
    }

}
