package com.renanferrari.testeandroidv2.application.ui.statements;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StatementsState {

  private final List<StatementItem> statementItems;
  private final boolean isLoading;

  private StatementsState(final List<StatementItem> statementItems, final boolean isLoading) {
    this.statementItems = statementItems;
    this.isLoading = isLoading;
  }

  public static StatementsState createDefault() {
    return new StatementsState(Collections.emptyList(), true);
  }

  public static StatementsState create(final List<StatementItem> statementItems) {
    return new StatementsState(statementItems, false);
  }

  public List<StatementItem> getStatementItems() {
    return statementItems;
  }

  public boolean isLoading() {
    return isLoading;
  }

  public StatementsState withLoading(final boolean isLoading) {
    return new StatementsState(statementItems, isLoading);
  }

  @Override public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final StatementsState that = (StatementsState) o;
    return isLoading == that.isLoading &&
        Objects.equals(statementItems, that.statementItems);
  }

  @Override public int hashCode() {
    return Objects.hash(statementItems, isLoading);
  }

  @Override @NonNull public String toString() {
    return "StatementsState{" +
        "statementItems=" + statementItems +
        ", isLoading=" + isLoading +
        '}';
  }
}