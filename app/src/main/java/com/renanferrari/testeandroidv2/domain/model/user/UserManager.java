package com.renanferrari.testeandroidv2.domain.model.user;

import com.renanferrari.testeandroidv2.common.Optional;
import io.reactivex.Observable;

public interface UserManager {

  void setUser(User user);

  User getUser();

  Observable<Optional<User>> getUserObservable();
}