package com.renanferrari.testeandroidv2.domain.model.auth;

import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import javax.inject.Inject;

public class AuthService {

  private final AuthApi authApi;
  private final UserManager userManager;

  @Inject public AuthService(final AuthApi authApi, final UserManager userManager) {
    this.authApi = authApi;
    this.userManager = userManager;
  }

  public Completable signIn(final String username, final String password) {
    return authApi.login(username, password)
        .doOnSuccess(userManager::setUser)
        .onErrorResumeNext(throwable -> Single.error(new ServerException(throwable)))
        .ignoreElement();
  }

  public Completable signOut() {
    return Completable.fromAction(() -> userManager.setUser(null));
  }

  public User getCurrentUser() {
    return userManager.getUser();
  }

  public Observable<User> observeCurrentUser() {
    return userManager.getUserObservable();
  }
}