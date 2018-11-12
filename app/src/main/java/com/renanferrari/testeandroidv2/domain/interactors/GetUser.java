package com.renanferrari.testeandroidv2.domain.interactors;

import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import io.reactivex.Maybe;
import javax.inject.Inject;

public class GetUser {

  private final UserManager userManager;

  @Inject public GetUser(final UserManager userManager) {
    this.userManager = userManager;
  }

  public Maybe<User> execute() {
    return Maybe.fromCallable(userManager::getUser);
  }
}