package com.renanferrari.testeandroidv2.infrastructure;

import java.util.List;

public class StatementsResponse {

  private final List<Statement> statementList;
  private final Error error;

  public StatementsResponse(final List<Statement> statementList, final Error error) {
    this.statementList = statementList;
    this.error = error;
  }

  public List<Statement> getStatementList() {
    return statementList;
  }

  public Error getError() {
    return error;
  }

  public static class Statement {
    private final String title;
    private final String desc;
    private final String date;
    private final double value;

    public Statement(final String title, final String desc, final String date, final double value) {
      this.title = title;
      this.desc = desc;
      this.date = date;
      this.value = value;
    }

    public String getTitle() {
      return title;
    }

    public String getDesc() {
      return desc;
    }

    public String getDate() {
      return date;
    }

    public double getValue() {
      return value;
    }
  }
}