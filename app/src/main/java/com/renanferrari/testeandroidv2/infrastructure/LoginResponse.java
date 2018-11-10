package com.renanferrari.testeandroidv2.infrastructure;

public class LoginResponse {

  private final UserAccount userAccount;
  private final Error error;

  public LoginResponse(final UserAccount userAccount, final Error error) {
    this.userAccount = userAccount;
    this.error = error;
  }

  public UserAccount getUserAccount() {
    return userAccount;
  }

  public Error getError() {
    return error;
  }

  @Override public String toString() {
    return "LoginResponse{" +
        "userAccount=" + userAccount +
        ", error=" + error +
        '}';
  }

  public static class UserAccount {

    private final int userId;
    private final String name;
    private final String bankAccount;
    private final String agency;
    private final double balance;

    public UserAccount(final int userId, final String name, final String bankAccount,
        final String agency, final double balance) {
      this.userId = userId;
      this.name = name;
      this.bankAccount = bankAccount;
      this.agency = agency;
      this.balance = balance;
    }

    public int getUserId() {
      return userId;
    }

    public String getName() {
      return name;
    }

    public String getBankAccount() {
      return bankAccount;
    }

    public String getAgency() {
      return agency;
    }

    public double getBalance() {
      return balance;
    }

    @Override public String toString() {
      return "UserAccount{" +
          "userId=" + userId +
          ", name='" + name + '\'' +
          ", bankAccount='" + bankAccount + '\'' +
          ", agency='" + agency + '\'' +
          ", balance=" + balance +
          '}';
    }
  }
}