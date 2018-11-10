package com.renanferrari.testeandroidv2.domain.interactors;

import com.renanferrari.testeandroidv2.domain.model.auth.AuthService;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidPasswordException;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidUsernameException;
import io.reactivex.Completable;
import javax.inject.Inject;

import static com.renanferrari.testeandroidv2.domain.model.auth.AuthValidation.isValidCpf;
import static com.renanferrari.testeandroidv2.domain.model.auth.AuthValidation.isValidEmail;
import static com.renanferrari.testeandroidv2.domain.model.auth.AuthValidation.isValidPassword;

public class Login {

  private final AuthService authService;

  @Inject public Login(final AuthService authService) {
    this.authService = authService;
  }

  public Completable execute(final String username, final String password) {
    if (!isValidEmail(username) && !isValidCpf(username)) {
      return Completable.error(new InvalidUsernameException());
    }
    if (!isValidPassword(password)) {
      return Completable.error(new InvalidPasswordException());
    }
    return authService.signIn(username, password);
  }
}