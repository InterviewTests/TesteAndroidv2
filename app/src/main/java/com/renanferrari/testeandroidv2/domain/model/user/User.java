package com.renanferrari.testeandroidv2.domain.model.user;

import java.math.BigDecimal;

public class User {

  private final int userId;
  private final String name;
  private final String bankAccount;
  private final String agency;
  private final BigDecimal balance;

  public User(final int userId, final String name, final String bankAccount, final String agency,
      final BigDecimal balance) {
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

  public BigDecimal getBalance() {
    return balance;
  }
}