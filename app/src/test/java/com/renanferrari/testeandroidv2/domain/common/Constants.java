package com.renanferrari.testeandroidv2.domain.common;

import com.renanferrari.testeandroidv2.domain.model.user.User;
import java.math.BigDecimal;

public class Constants {

  public static final String VALID_CPF = "999.896.200-59";
  public static final String INVALID_CPF = "999.999.999-99";
  public static final String VALID_EMAIL = "name@email.com";
  public static final String INVALID_EMAIL = "nameemail.com";
  public static final String VALID_PASSWORD = "Test@1";
  public static final String INVALID_PASSWORD_NO_CAPITAL_LETTER = "test@1";
  public static final String INVALID_PASSWORD_NO_SPECIAL_CHARACTER = "Test1";

  public static final User VALID_USER =
      new User(0, "User", "00000-00", "0000-0", BigDecimal.valueOf(1000));

  public static final RuntimeException RUNTIME_EXCEPTION = new RuntimeException();
}