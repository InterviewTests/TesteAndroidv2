package com.renanferrari.testeandroidv2.domain.model.statements;

import androidx.annotation.NonNull;
import java.math.BigDecimal;
import java.util.Objects;
import org.threeten.bp.LocalDate;

public class Statement {

  private final String title;
  private final String description;
  private final LocalDate date;
  private final BigDecimal value;

  private Statement(final Builder builder) {
    title = builder.title;
    description = builder.description;
    date = builder.date;
    value = builder.value;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getDate() {
    return date;
  }

  public BigDecimal getValue() {
    return value;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String title;
    private String description;
    private LocalDate date;
    private BigDecimal value;

    private Builder() {}

    public Builder title(final String title) {
      this.title = title;
      return this;
    }

    public Builder description(final String description) {
      this.description = description;
      return this;
    }

    public Builder date(final LocalDate date) {
      this.date = date;
      return this;
    }

    public Builder value(final BigDecimal value) {
      this.value = value;
      return this;
    }

    public Statement build() {
      return new Statement(this);
    }
  }

  @Override public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Statement statement = (Statement) o;
    return Objects.equals(title, statement.title) &&
        Objects.equals(description, statement.description) &&
        Objects.equals(date, statement.date) &&
        Objects.equals(value, statement.value);
  }

  @Override public int hashCode() {
    return Objects.hash(title, description, date, value);
  }

  @Override @NonNull public String toString() {
    return "Statement{" +
        "title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", date=" + date +
        ", value=" + value +
        '}';
  }
}