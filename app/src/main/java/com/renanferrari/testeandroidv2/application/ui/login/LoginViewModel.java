package com.renanferrari.testeandroidv2.application.ui.login;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.renanferrari.testeandroidv2.application.common.providers.SchedulerProvider;
import com.renanferrari.testeandroidv2.domain.interactors.Login;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidPasswordException;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidUsernameException;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

  private static final String TAG = LoginViewModel.class.getSimpleName();

  private final Login login;
  private final SchedulerProvider schedulerProvider;

  private final MutableLiveData<LoginState> stateLiveData = new MutableLiveData<>();
  private final CompositeDisposable disposables = new CompositeDisposable();

  @Inject public LoginViewModel(final Login login, final SchedulerProvider schedulerProvider) {
    this.login = login;
    this.schedulerProvider = schedulerProvider;
    this.stateLiveData.setValue(LoginState.createDefault());
  }

  @Override protected void onCleared() {
    disposables.dispose();
    super.onCleared();
  }

  public void onUsernameChanged(final String username) {
    final LoginState state = stateLiveData.getValue();
    state.setUsername(username);
    state.clearErrors();
    stateLiveData.setValue(state);
  }

  public void onPasswordChanged(final String password) {
    final LoginState state = stateLiveData.getValue();
    state.setPassword(password);
    state.clearErrors();
    stateLiveData.setValue(state);
  }

  public void onLoginRequested() {
    final LoginState state = stateLiveData.getValue();
    disposables.add(login.execute(state.getUsername(), state.getPassword())
        .compose(schedulerProvider.applySchedulers())
        .doOnSubscribe(disposable -> {
          final LoginState newState = stateLiveData.getValue();
          newState.setLoading(true);
          stateLiveData.setValue(newState);
        })
        .subscribe(
            () -> {
              final LoginState newState = stateLiveData.getValue();
              newState.setLoading(false);
              newState.setLoggedIn(true);
              stateLiveData.setValue(newState);
            },
            throwable -> {
              final LoginState newState = stateLiveData.getValue();
              newState.setLoading(false);
              if (throwable instanceof InvalidUsernameException) {
                newState.setUsernameError("Usuário inválido!");
              } else if (throwable instanceof InvalidPasswordException) {
                newState.setPasswordError("Senha inválida!");
              } else {
                Log.d(TAG, "Erro desconhecido!");
              }
              stateLiveData.setValue(newState);
            }));
  }

  public LiveData<LoginState> getObservableState() {
    return stateLiveData;
  }
}