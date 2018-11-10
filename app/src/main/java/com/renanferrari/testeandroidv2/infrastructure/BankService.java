package com.renanferrari.testeandroidv2.infrastructure;

import com.renanferrari.testeandroidv2.domain.model.auth.AuthApi;
import com.renanferrari.testeandroidv2.domain.model.user.User;
import io.reactivex.Single;
import java.math.BigDecimal;
import javax.inject.Inject;

import static java.math.RoundingMode.HALF_UP;

public class BankService implements AuthApi {

  private final BankApi bankApi;

  @Inject public BankService(final BankApi bankApi) {
    this.bankApi = bankApi;
  }

  @Override public Single<User> login(final String username, final String password) {
    return bankApi.login(username, password)
        .map(loginResponse -> {
          final LoginResponse.UserAccount userAccount = loginResponse.getUserAccount();
          if (userAccount != null) {
            return User.builder()
                .userId(userAccount.getUserId())
                .name(userAccount.getName())
                .bankAccount(userAccount.getBankAccount())
                .agency(userAccount.getAgency())
                .balance(BigDecimal.valueOf(userAccount.getBalance()).setScale(2, HALF_UP))
                .build();
          } else {
            throw new RuntimeException(loginResponse.getError().getMessage());
          }
        });
  }
}