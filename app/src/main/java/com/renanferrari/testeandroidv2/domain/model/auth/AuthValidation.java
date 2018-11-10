package com.renanferrari.testeandroidv2.domain.model.auth;

import androidx.core.util.PatternsCompat;
import java.util.regex.Pattern;

public class AuthValidation {

  private static Pattern CPF_PATTERN_GENERIC =
      Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
  private static Pattern CPF_PATTERN_NUMBERS = Pattern.compile(
      "(?=^((?!((([0]{11})|([1]{11})|([2]{11})|([3]{11})|([4]{11})|([5]{11})|([6]{11})|([7]{11})|([8]{11})|([9]{11})))).)*$)([0-9]{11})");

  // Reference: https://stackoverflow.com/a/15808057/518179
  public static boolean isValidEmail(final String email) {
    return email != null && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches();
  }

  // Reference: https://gist.github.com/ademar111190/49da8307784807f4ec6e32dc67ef629b
  public static boolean isValidCpf(String cpf) {
    if (cpf != null && CPF_PATTERN_GENERIC.matcher(cpf).matches()) {
      cpf = cpf.replaceAll("-|\\.", "");
      if (cpf != null && CPF_PATTERN_NUMBERS.matcher(cpf).matches()) {
        int[] numbers = new int[11];
        for (int i = 0; i < 11; i++) numbers[i] = Character.getNumericValue(cpf.charAt(i));
        int i;
        int sum = 0;
        int factor = 100;
        for (i = 0; i < 9; i++) {
          sum += numbers[i] * factor;
          factor -= 10;
        }
        int leftover = sum % 11;
        leftover = leftover == 10 ? 0 : leftover;
        if (leftover == numbers[9]) {
          sum = 0;
          factor = 110;
          for (i = 0; i < 10; i++) {
            sum += numbers[i] * factor;
            factor -= 10;
          }
          leftover = sum % 11;
          leftover = leftover == 10 ? 0 : leftover;
          return leftover == numbers[10];
        }
      }
    }
    return false;
  }

  public static boolean isValidPassword(final String password) {
    if (password == null) {
      return false;
    }

    boolean hasUppercase = false;
    boolean hasSpecial = false;

    for (final char c : password.toCharArray()) {
      if (Character.isUpperCase(c)) {
        hasUppercase = true;
      } else if (Character.isLowerCase(c) || Character.isDigit(c)) {
        continue;
      } else if (Character.isDigit(c)) {
        continue;
      } else {
        hasSpecial = true;
      }

      if (hasUppercase && hasSpecial) {
        return true;
      }
    }
    return false;
  }
}