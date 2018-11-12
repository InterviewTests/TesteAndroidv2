package com.renanferrari.testeandroidv2.application.ui.statements;

public class UserState {
  private final String name;
  private final String bankAccount;
  private final String balance;

  private UserState(final Builder builder) {
    name = builder.name;
    bankAccount = builder.bankAccount;
    balance = builder.balance;
  }

  public String getName() {
    return name;
  }

  public String getBankAccount() {
    return bankAccount;
  }

  public String getBalance() {
    return balance;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String name;
    private String bankAccount;
    private String balance;

    private Builder() {}

    public Builder name(final String name) {
      this.name = name;
      return this;
    }

    public Builder bankAccount(final String bankAccount) {
      this.bankAccount = bankAccount;
      return this;
    }

    public Builder balance(final String balance) {
      this.balance = balance;
      return this;
    }

    public UserState build() {
      return new UserState(this);
    }
  }
}