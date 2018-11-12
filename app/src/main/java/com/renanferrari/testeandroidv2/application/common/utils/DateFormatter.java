package com.renanferrari.testeandroidv2.application.common.utils;

import java.util.Locale;
import javax.inject.Inject;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;
import org.threeten.bp.temporal.TemporalAccessor;

public class DateFormatter {

  private final Locale locale;

  @Inject public DateFormatter(final Locale locale) {
    this.locale = locale;
  }

  public String format(final TemporalAccessor temporal) {
    return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
        .withLocale(locale)
        .format(temporal);
  }
}