package com.renanferrari.testeandroidv2.domain.model.user;

import io.reactivex.Observable;

public interface UserManager {

  void setUser(User user);

  User getUser();

  Observable<User> getUserObservable();
}