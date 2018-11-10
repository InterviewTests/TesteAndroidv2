package com.renanferrari.testeandroidv2.domain.model.user;

import java.math.BigDecimal;
import java.util.Objects;

public class User {

  private final int userId;
  private final String name;
  private final String bankAccount;
  private final String agency;
  private final BigDecimal balance;

  private User(final Builder builder) {
    userId = builder.userId;
    name = builder.name;
    bankAccount = builder.bankAccount;
    agency = builder.agency;
    balance = builder.balance;
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

  public BigDecimal getBalance() {
    return balance;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private int userId;
    private String name;
    private String bankAccount;
    private String agency;
    private BigDecimal balance;

    private Builder() {}

    public Builder userId(final int userId) {
      this.userId = userId;
      return this;
    }

    public Builder name(final String name) {
      this.name = name;
      return this;
    }

    public Builder bankAccount(final String bankAccount) {
      this.bankAccount = bankAccount;
      return this;
    }

    public Builder agency(final String agency) {
      this.agency = agency;
      return this;
    }

    public Builder balance(final BigDecimal balance) {
      this.balance = balance;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }

  @Override public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final User user = (User) o;
    return userId == user.userId &&
        Objects.equals(name, user.name) &&
        Objects.equals(bankAccount, user.bankAccount) &&
        Objects.equals(agency, user.agency) &&
        Objects.equals(balance, user.balance);
  }

  @Override public int hashCode() {
    return Objects.hash(userId, name, bankAccount, agency, balance);
  }
}