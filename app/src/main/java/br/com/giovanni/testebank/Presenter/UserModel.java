package br.com.giovanni.testebank.Presenter;

//interface IUserAccount {
//    int getUserId();
//    int setUserId();
//
//    String getName();
//    String setName();
//
//    int getBankAccount();
//    int setBankAccount();
//    int getAgency ();
//    double getBalance();
//}

public class UserModel {

    public final UserAccount usuario ;

    public UserModel (){
        this.usuario = new UserAccount();
    }

    public UserAccount getUsuario() {
        return usuario;
    }

}


class UserAccount {
    private int userId;
    private String name;
    private int bankAccount;
    private int agency;
    private double balance;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(int bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getAgency() {
        return agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "userAccount:  "
                + "\nuserId: " + getUserId()
                + "\nname: " + getName()
                + "\nbankAccount: " + getBankAccount()
                + "\nagency:" + getAgency()
                + "\nbalance: " + getBalance();
    }


}

//{
////        "userAccount": {
////        "userId": 1,
////        "name": "Jose da Silva Teste",
////        "bankAccount": "2050",
////        "agency": "012314564",
////        "balance": 3.3445
////        },
////        "error": {}
////        }