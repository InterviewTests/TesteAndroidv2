package br.com.kakobotasso.testeandroidv2.login;

public class LoginResponse {
    private int userId;
    private String name;
    private String agency;
    private String bankAccount;
    private double balance;
    private Errors errors = null;

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

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        if (errors == null) {
            return false;
        }

        return true;
    }

    public String getErrorMessage() {
        if (hasErrors()) {
            return errors.msg;
        }

        return "";
    }

    public class Errors {
        private String msg;

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
