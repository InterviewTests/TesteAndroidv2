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
    disposables.add(getUser.execute().subscribe(user -> {
      final LoginState state = loginStateLiveData.getValue();
      state.setLoggedIn(true);
      loginStateLiveData.setValue(state);
    }));
  }

  public void onUsernameChanged(final String username) {
    final LoginState state = loginStateLiveData.getValue();
    state.setUsername(username);
    state.clearErrors();
    loginStateLiveData.setValue(state);
  }

  public void onPasswordChanged(final String password) {
    final LoginState state = loginStateLiveData.getValue();
    state.setPassword(password);
    state.clearErrors();
    loginStateLiveData.setValue(state);
  }

  public void onLoginRequested() {
    final LoginState state = loginStateLiveData.getValue();
    disposables.add(login.execute(state.getUsername(), state.getPassword())
        .compose(schedulerProvider.applySchedulers())
        .doOnSubscribe(disposable -> {
          final LoginState newState = loginStateLiveData.getValue();
          newState.setLoading(true);
          loginStateLiveData.setValue(newState);
        })
        .subscribe(
            () -> {
              final LoginState newState = loginStateLiveData.getValue();
              newState.setLoading(false);
              newState.setLoggedIn(true);
              loginStateLiveData.setValue(newState);
            },
            throwable -> {
              final LoginState newState = loginStateLiveData.getValue();
              newState.setLoading(false);
              if (throwable instanceof InvalidUsernameException) {
                newState.setUsernameError(
                    resourceProvider.getString(R.string.message_invalid_username));
              } else if (throwable instanceof InvalidPasswordException) {
                newState.setPasswordError(
                    resourceProvider.getString(R.string.message_invalid_password));
              } else {
                Log.d(TAG, "Erro desconhecido!");
              }
              loginStateLiveData.setValue(newState);
            }));
  }

  public LiveData<LoginState> getObservableLoginState() {
    return loginStateLiveData;
  }
}