package com.renanferrari.testeandroidv2.infrastructure;

import com.renanferrari.testeandroidv2.domain.model.auth.AuthApi;
import com.renanferrari.testeandroidv2.domain.model.statements.Statement;
import com.renanferrari.testeandroidv2.domain.model.statements.StatementsRepository;
import com.renanferrari.testeandroidv2.domain.model.user.User;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.threeten.bp.LocalDate;

import static java.math.RoundingMode.HALF_UP;

public class BankService implements AuthApi, StatementsRepository {

  private final BankApi bankApi;

  @Inject public BankService(final BankApi bankApi) {
    this.bankApi = bankApi;
  }

  @Override public Single<User> login(final String username, final String password) {
    return bankApi.login(username, password)
        .map(loginResponse -> {
          final LoginResponse.UserAccount userAccount = loginResponse.getUserAccount();
          if (userAccount != null) {
            return userAccount;
          } else {
            throw new RuntimeException(loginResponse.getError().getMessage());
          }
        })
        .map(userAccount -> User.builder()
            .userId(userAccount.getUserId())
            .name(userAccount.getName())
            .bankAccount(userAccount.getBankAccount())
            .agency(userAccount.getAgency())
            .balance(BigDecimal.valueOf(userAccount.getBalance()).setScale(2, HALF_UP))
            .build());
  }

  @Override public Single<List<Statement>> getRecentStatements(final int userId) {
    return bankApi.statements(userId)
        .map(statementsResponse -> {
          final List<StatementsResponse.Statement> statementResponseStatements =
              statementsResponse.getStatementList();
          if (statementResponseStatements != null) {
            return statementResponseStatements;
          } else {
            throw new RuntimeException(statementsResponse.getError().getMessage());
          }
        })
        .flatMapObservable(Observable::fromIterable)
        .map(statement -> Statement.builder()
            .title(statement.getTitle())
            .description(statement.getDesc())
            .date(LocalDate.parse(statement.getDate()))
            .value(BigDecimal.valueOf(statement.getValue()).setScale(2, HALF_UP))
            .build())
        .toList();
  }
}