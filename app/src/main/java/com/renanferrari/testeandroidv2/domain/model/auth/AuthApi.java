package com.renanferrari.testeandroidv2.domain.model.auth;

import com.renanferrari.testeandroidv2.domain.model.user.User;
import io.reactivex.Single;

public interface AuthApi {
  Single<User> login(String username, String password);
}