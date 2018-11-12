package com.renanferrari.testeandroidv2.infrastructure;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.google.gson.Gson;
import com.renanferrari.testeandroidv2.common.Optional;
import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import io.reactivex.Observable;
import javax.inject.Inject;

public class LocalUserManager implements UserManager {

  private final Preference<Optional<User>> userPreference;

  @Inject public LocalUserManager(final SharedPreferences preferences, final Gson gson) {
    userPreference = RxSharedPreferences.create(preferences).getObject("user", Optional.absent(),
        new Preference.Converter<Optional<User>>() {
          @Override @NonNull public Optional<User> deserialize(@NonNull final String serialized) {
            return Optional.fromNullable(gson.fromJson(serialized, User.class));
          }

          @Override @NonNull public String serialize(@NonNull final Optional<User> user) {
            return gson.toJson(user.orNull());
          }
        });
  }

  @Override public void setUser(final User user) {
    userPreference.set(Optional.fromNullable(user));
  }

  @Override public User getUser() {
    return userPreference.get().orNull();
  }

  @Override public Observable<Optional<User>> getUserObservable() {
    return userPreference.asObservable();
  }
}