package Domain;

import org.json.JSONException;
import org.json.JSONObject;

public class UserAccount {
    private int userId;
    private String name;
    private String bankAccount;
    private String agency;
    private double balance;

    public UserAccount(JSONObject object){
        try {
            setUserId(object.getInt("userId"));
            setName(object.getString("name"));
            setBankAccount(object.getString("bankAccount"));
            setAgency(object.getString("agency"));
            setBalance(object.getDouble("balance"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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
}
