package com.renanferrari.testeandroidv2.infrastructure;

public class Error {

  private final int code;
  private final String message;

  public Error(final int code, final String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  @Override public String toString() {
    return "Error{" +
        "code=" + code +
        ", message='" + message + '\'' +
        '}';
  }
}