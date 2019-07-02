package com.example.santandertestebank.model.domain;

public class UserAccount {

    public UserAccount repository;

    private String user;
    private String password;
    private long userId;
    private String name;
    private String bankAccount;
    private String agency;
    private double balance;

    public UserAccount(String user, String password, long userId, String name, String bankAccount,
                       String agency, double balance) {
        this.user = user;
        this.password = password;
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public void validateId() throws Exception {
        if (userId < 1) throw new Exception ("O id do usuário é inválido");
    }

    public void validateLogin() throws Exception {
        if (user.isEmpty ()) {
            throw new Exception ("Digite um email ou CPF para logar)");

        } else if (!user.matches ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"))
//            || (!user.matches ()) FALTA VALIDAR O CPF;
            throw new Exception ("Digite um email ou CPF válido");
    }

    public void validadePassword() throws Exception {
        if (password.isEmpty ()) {
            throw new Exception ("Digite sua senha para logar");

        } else if (!password.matches ("^((?=.[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!*?])(?=\\S+$).{3,})$")) {
            throw new Exception ("A senha precisa ter pelo menos 1 letra maiúscula, 1 caractere especial e um cacactere alfanumérico");
        }

    }

}