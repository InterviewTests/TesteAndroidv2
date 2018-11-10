package com.renanferrari.testeandroidv2.domain.model.auth;

public class ServerException extends RuntimeException {

  public ServerException(final Throwable throwable) {
    super(throwable.getMessage(), throwable.getCause());
  }
}