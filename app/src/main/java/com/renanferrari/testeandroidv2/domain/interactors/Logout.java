package com.renanferrari.testeandroidv2.domain.interactors;

import com.renanferrari.testeandroidv2.domain.model.auth.AuthService;
import io.reactivex.Completable;
import javax.inject.Inject;

public class Logout {

  private final AuthService authService;

  @Inject public Logout(final AuthService authService) {
    this.authService = authService;
  }

  public Completable execute() {
    return authService.signOut();
  }
}