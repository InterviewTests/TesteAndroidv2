package com.renanferrari.testeandroidv2.domain.common;

import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.infrastructure.Error;
import com.renanferrari.testeandroidv2.infrastructure.LoginResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Constants {

  public static final String VALID_CPF = "999.896.200-59";
  public static final String INVALID_CPF = "999.999.999-99";
  public static final String VALID_EMAIL = "name@email.com";
  public static final String INVALID_EMAIL = "nameemail.com";
  public static final String VALID_PASSWORD = "Test@1";
  public static final String INVALID_PASSWORD_NO_CAPITAL_LETTER = "test@1";
  public static final String INVALID_PASSWORD_NO_SPECIAL_CHARACTER = "Test1";

  public static final User VALID_USER = User.builder()
      .userId(0)
      .name("User")
      .bankAccount("0000000")
      .agency("00000")
      .balance(BigDecimal.valueOf(1000).setScale(2, RoundingMode.HALF_UP))
      .build();

  public static final RuntimeException RUNTIME_EXCEPTION = new RuntimeException();

  public static final LoginResponse.UserAccount VALID_USER_ACCOUNT =
      new LoginResponse.UserAccount(0, "User", "0000000", "00000", 1000.0);

  public static final Error EMPTY_ERROR = new Error(0, null);

  public static final Error INVALID_USER_OR_PASSWORD_ERROR =
      new Error(53, "Usuário ou senha incorreta");

  public static final LoginResponse SUCCESSFUL_LOGIN_RESPONSE =
      new LoginResponse(VALID_USER_ACCOUNT, EMPTY_ERROR);

  public static final LoginResponse FAILED_LOGIN_RESPONSE =
      new LoginResponse(null, INVALID_USER_OR_PASSWORD_ERROR);
}