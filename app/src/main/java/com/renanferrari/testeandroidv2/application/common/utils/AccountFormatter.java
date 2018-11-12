package com.renanferrari.testeandroidv2.application.common.utils;

public class AccountFormatter {

  private static final String PATTERN = "#### / ##.######-#";

  public static String format(final String bankAccount, final String agency) {
    final String concat = bankAccount + agency;
    final StringBuilder stringBuilder = new StringBuilder();
    int concatIndex = 0;
    for (char patternChar : PATTERN.toCharArray()) {
      if (patternChar == '#') {
        stringBuilder.append(concat.charAt(concatIndex));
        concatIndex++;
      } else {
        stringBuilder.append(patternChar);
      }
    }
    return stringBuilder.toString();
  }
}