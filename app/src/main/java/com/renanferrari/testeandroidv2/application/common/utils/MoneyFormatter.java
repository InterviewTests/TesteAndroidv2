package com.renanferrari.testeandroidv2.application.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.inject.Inject;

public class MoneyFormatter {

  private static final String SIGNED_PATTERN = "+\u00A4#,##0.00;-\u00A4#";

  private final DecimalFormat decimalFormat;
  private final DecimalFormat signedDecimalFormat;

  @Inject public MoneyFormatter(final Locale locale) {
    this.decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
    this.signedDecimalFormat =
        new DecimalFormat(SIGNED_PATTERN, decimalFormat.getDecimalFormatSymbols());
  }

  public String format(final BigDecimal value) {
    return decimalFormat.format(value);
  }

  public String formatWithSign(final BigDecimal value) {
    return signedDecimalFormat.format(value);
  }
}