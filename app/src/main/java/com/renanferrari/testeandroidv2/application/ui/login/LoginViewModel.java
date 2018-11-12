package com.renanferrari.testeandroidv2.application.ui.login;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.renanferrari.testeandroidv2.R;
import com.renanferrari.testeandroidv2.application.common.providers.ResourceProvider;
import com.renanferrari.testeandroidv2.application.common.providers.SchedulerProvider;
import com.renanferrari.testeandroidv2.domain.interactors.GetUser;
import com.renanferrari.testeandroidv2.domain.interactors.Login;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidPasswordException;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidUsernameException;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

  private static final String TAG = LoginViewModel.class.getSimpleName();

  private final Login login;
  private final GetUser getUser;
  private final ResourceProvider resourceProvider;
  private final SchedulerProvider schedulerProvider;

  private final MutableLiveData<LoginState> loginStateLiveData = new MutableLiveData<>();
  private final CompositeDisposable disposables = new CompositeDisposable();

  @Inject public LoginViewModel(final Login login, final GetUser getUser,
      final ResourceProvider resourceProvider,
      final SchedulerProvider schedulerProvider) {
    this.login = login;
    this.getUser = getUser;
    this.resourceProvider = resourceProvider;
    this.schedulerProvider = schedulerProvider;

    this.loginStateLiveData.setValue(LoginState.createDefault());
  }

  @Override protected void onCleared() {
    disposables.dispose();
    super.onCleared();
  }

  public void onUserRequested() {
    disposables.add(getUser.execute()
        .subscribe(user -> loginStateLiveData.setValue(loginStateLiveData.getValue()
            .withLoggedIn(true))));
  }

  public void onUsernameChanged(final String username) {
    loginStateLiveData.setValue(loginStateLiveData.getValue()
        .withUsername(username)
        .withoutErrors());
  }

  public void onPasswordChanged(final String password) {
    loginStateLiveData.setValue(loginStateLiveData.getValue()
        .withPassword(password)
        .withoutErrors());
  }

  public void onLoginRequested() {
    final LoginState state = loginStateLiveData.getValue();
    disposables.add(login.execute(state.getUsername(), state.getPassword())
        .compose(schedulerProvider.applySchedulers())
        .doOnSubscribe(
            d -> loginStateLiveData.setValue(
                loginStateLiveData.getValue().withLoading(true)))
        .subscribe(
            () -> loginStateLiveData.setValue(
                loginStateLiveData.getValue().withLoading(false).withLoggedIn(true)),
            throwable -> {
              final LoginState.Builder stateBuilder = loginStateLiveData.getValue().toBuilder();
              stateBuilder.isLoading(false);
              if (throwable instanceof InvalidUsernameException) {
                stateBuilder.usernameError(
                    resourceProvider.getString(R.string.message_invalid_username));
              } else if (throwable instanceof InvalidPasswordException) {
                stateBuilder.passwordError(
                    resourceProvider.getString(R.string.message_invalid_password));
              } else {
                Log.d(TAG, "Error logging in: " + throwable.getMessage());
              }
              loginStateLiveData.setValue(stateBuilder.build());
            }));
  }

  public LiveData<LoginState> getObservableLoginState() {
    return loginStateLiveData;
  }
}