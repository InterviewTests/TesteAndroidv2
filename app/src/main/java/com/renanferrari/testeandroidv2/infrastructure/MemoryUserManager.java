package com.renanferrari.testeandroidv2.infrastructure;

import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MemoryUserManager implements UserManager {

  private final BehaviorSubject<User> userBehaviorSubject = BehaviorSubject.create();

  @Override public void setUser(final User user) {
    userBehaviorSubject.onNext(user);
  }

  @Override public User getUser() {
    return userBehaviorSubject.getValue();
  }

  @Override public Observable<User> getUserObservable() {
    return userBehaviorSubject;
  }
}