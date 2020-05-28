package br.com.dpassos.bankandroid.login.business;

public class UserAccount {
    public String userId;
    public String name;
    public String bankAccount;
    public String agency;
    public double balance;

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", agency='" + agency + '\'' +
                ", balance=" + balance +
                '}';
    }
}