package com.renanferrari.testeandroidv2.application.ui.statements;

import androidx.annotation.NonNull;
import java.util.Objects;

public class UserState {
  private final String name;
  private final String account;
  private final String balance;

  private UserState(final Builder builder) {
    name = builder.name;
    account = builder.account;
    balance = builder.balance;
  }

  public String getName() {
    return name;
  }

  public String getAccount() {
    return account;
  }

  public String getBalance() {
    return balance;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String name;
    private String account;
    private String balance;

    private Builder() {}

    public Builder name(final String name) {
      this.name = name;
      return this;
    }

    public Builder account(final String account) {
      this.account = account;
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

  @Override public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final UserState userState = (UserState) o;
    return Objects.equals(name, userState.name) &&
        Objects.equals(account, userState.account) &&
        Objects.equals(balance, userState.balance);
  }

  @Override public int hashCode() {
    return Objects.hash(name, account, balance);
  }

  @Override @NonNull public String toString() {
    return "UserState{" +
        "name='" + name + '\'' +
        ", account='" + account + '\'' +
        ", balance='" + balance + '\'' +
        '}';
  }
}