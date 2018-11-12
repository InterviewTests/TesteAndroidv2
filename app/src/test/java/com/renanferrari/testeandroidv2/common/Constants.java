package com.renanferrari.testeandroidv2.common;

import com.renanferrari.testeandroidv2.domain.model.statements.Statement;
import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.infrastructure.Error;
import com.renanferrari.testeandroidv2.infrastructure.LoginResponse;
import com.renanferrari.testeandroidv2.infrastructure.StatementsResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import org.threeten.bp.LocalDate;

public class Constants {

  public static final String VALID_CPF = "999.896.200-59";
  public static final String INVALID_CPF = "999.999.999-99";
  public static final String VALID_EMAIL = "name@email.com";
  public static final String INVALID_EMAIL = "nameemail.com";
  public static final String VALID_PASSWORD = "Test@1";
  public static final String INVALID_PASSWORD_NO_CAPITAL_LETTER = "test@1";
  public static final String INVALID_PASSWORD_NO_SPECIAL_CHARACTER = "Test1";

  public static final RuntimeException RUNTIME_EXCEPTION = new RuntimeException();

  public static final Error EMPTY_ERROR = new Error(0, null);

  public static final Error INVALID_USER_OR_PASSWORD_ERROR =
      new Error(53, "Usuário ou senha incorreta");

  public static final Error USER_NOT_FOUND_ERROR =
      new Error(53, "Usuário não encontrado");

  public static final User VALID_USER = User.builder()
      .userId(1)
      .name("Jose da Silva Teste")
      .bankAccount("2050")
      .agency("012314564")
      .balance(BigDecimal.valueOf(3.3445).setScale(2, RoundingMode.HALF_UP))
      .build();

  public static final LoginResponse.UserAccount VALID_USER_ACCOUNT =
      new LoginResponse.UserAccount(1, "Jose da Silva Teste", "2050", "012314564", 3.3445);

  public static final LoginResponse SUCCESSFUL_LOGIN_RESPONSE =
      new LoginResponse(VALID_USER_ACCOUNT, EMPTY_ERROR);

  public static final LoginResponse FAILED_LOGIN_RESPONSE =
      new LoginResponse(null, INVALID_USER_OR_PASSWORD_ERROR);

  public static final Statement VALID_STATEMENT = Statement.builder()
      .title("title")
      .description("desc")
      .date(LocalDate.of(2010, 1, 1))
      .value(BigDecimal.valueOf(1000).setScale(2, RoundingMode.HALF_UP))
      .build();

  public static final List<Statement> VALID_STATEMENT_LIST =
      Arrays.asList(VALID_STATEMENT, VALID_STATEMENT, VALID_STATEMENT);

  public static final StatementsResponse.Statement VALID_RAW_STATEMENT =
      new StatementsResponse.Statement("title", "desc", "2010-01-01", 1000.0);

  public static final List<StatementsResponse.Statement> VALID_RAW_STATEMENT_LIST =
      Arrays.asList(VALID_RAW_STATEMENT, VALID_RAW_STATEMENT, VALID_RAW_STATEMENT);

  public static final StatementsResponse SUCCESSFUL_STATEMENTS_RESPONSE =
      new StatementsResponse(VALID_RAW_STATEMENT_LIST, EMPTY_ERROR);

  public static final StatementsResponse FAILED_STATEMENTS_RESPONSE =
      new StatementsResponse(null, USER_NOT_FOUND_ERROR);
}