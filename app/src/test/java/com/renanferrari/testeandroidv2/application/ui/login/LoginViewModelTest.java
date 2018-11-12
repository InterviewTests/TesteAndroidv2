package com.renanferrari.testeandroidv2.application.ui.login;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import com.renanferrari.testeandroidv2.application.common.providers.ResourceProvider;
import com.renanferrari.testeandroidv2.application.common.providers.SchedulerProvider;
import com.renanferrari.testeandroidv2.domain.interactors.GetUser;
import com.renanferrari.testeandroidv2.domain.interactors.Login;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidPasswordException;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidUsernameException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static com.renanferrari.testeandroidv2.common.Constants.VALID_EMAIL;
import static com.renanferrari.testeandroidv2.common.Constants.VALID_PASSWORD;
import static com.renanferrari.testeandroidv2.common.Constants.VALID_USER;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class LoginViewModelTest {

  @Mock private Login login;
  @Mock private GetUser getUser;
  @Mock private ResourceProvider resourceProvider;

  @Mock private Observer<LoginState> stateObserver;

  @Captor private ArgumentCaptor<LoginState> stateArgumentCaptor;

  @Rule public TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  private LoginViewModel loginViewModel;

  @Before public void setUp() {
    loginViewModel = new LoginViewModel(login, getUser, resourceProvider,
        SchedulerProvider.forTesting());
  }

  @Test public void onUserRequested_withLoggedInUser_shouldUpdateState() {
    when(getUser.execute()).thenReturn(Maybe.just(VALID_USER));

    loginViewModel.getObservableLoginState().observeForever(stateObserver);
    loginViewModel.onUserRequested();

    final LoginState expectedInitialState = LoginState.createDefault();
    final LoginState expectedFinalState = expectedInitialState.withLoggedIn(true);

    verify(stateObserver, times(2)).onChanged(stateArgumentCaptor.capture());

    final List<LoginState> allStates = stateArgumentCaptor.getAllValues();

    assertThat(allStates.get(0)).named("initial state").isEqualTo(expectedInitialState);
    assertThat(allStates.get(1)).named("final state").isEqualTo(expectedFinalState);
  }

  @Test public void onUserRequested_withNoUser_shouldNotUpdateState() {
    when(getUser.execute()).thenReturn(Maybe.empty());

    loginViewModel.getObservableLoginState().observeForever(stateObserver);
    loginViewModel.onUserRequested();

    verify(stateObserver, times(1)).onChanged(stateArgumentCaptor.capture());
  }

  @Test public void onUsernameChanged() {
    loginViewModel.getObservableLoginState().observeForever(stateObserver);
    loginViewModel.onUsernameChanged(VALID_EMAIL);

    final LoginState expectedInitialState = LoginState.createDefault();
    final LoginState expectedFinalState = expectedInitialState.withUsername(VALID_EMAIL);

    verify(stateObserver, times(2)).onChanged(stateArgumentCaptor.capture());

    final List<LoginState> allStates = stateArgumentCaptor.getAllValues();

    assertThat(allStates.get(0)).named("initial state").isEqualTo(expectedInitialState);
    assertThat(allStates.get(1)).named("final state").isEqualTo(expectedFinalState);
  }

  @Test public void onPasswordChanged() {
    loginViewModel.getObservableLoginState().observeForever(stateObserver);
    loginViewModel.onPasswordChanged(VALID_PASSWORD);

    final LoginState expectedInitialState = LoginState.createDefault();
    final LoginState expectedFinalState = expectedInitialState.withPassword(VALID_PASSWORD);

    verify(stateObserver, times(2)).onChanged(stateArgumentCaptor.capture());

    final List<LoginState> allStates = stateArgumentCaptor.getAllValues();

    assertThat(allStates.get(0)).named("initial state").isEqualTo(expectedInitialState);
    assertThat(allStates.get(1)).named("final state").isEqualTo(expectedFinalState);
  }

  @Test public void onLoginRequested_withSuccessfulLogin_shouldUpdateLoggedInState() {
    when(login.execute(anyString(), anyString())).thenReturn(Completable.complete());

    loginViewModel.getObservableLoginState().observeForever(stateObserver);
    loginViewModel.onLoginRequested();

    final LoginState expectedInitialState = LoginState.createDefault();
    final LoginState expectedLoadingState = expectedInitialState.withLoading(true);
    final LoginState expectedFinalState =
        expectedLoadingState.withLoading(false).withLoggedIn(true);

    verify(stateObserver, times(3)).onChanged(stateArgumentCaptor.capture());

    final List<LoginState> allStates = stateArgumentCaptor.getAllValues();

    assertThat(allStates.get(0)).named("initial state").isEqualTo(expectedInitialState);
    assertThat(allStates.get(1)).named("loading state").isEqualTo(expectedLoadingState);
    assertThat(allStates.get(2)).named("final state").isEqualTo(expectedFinalState);
  }

  @Test public void onLoginRequested_withInvalidUsername_shouldUpdateErrorState() {
    when(login.execute(anyString(), anyString())).thenReturn(
        Completable.error(new InvalidUsernameException()));
    when(resourceProvider.getString(anyInt())).thenReturn("Error");

    loginViewModel.getObservableLoginState().observeForever(stateObserver);
    loginViewModel.onLoginRequested();

    verify(stateObserver, times(3)).onChanged(stateArgumentCaptor.capture());

    assertThat(stateArgumentCaptor.getValue().getUsernameError()).isEqualTo("Error");
    assertThat(stateArgumentCaptor.getValue().getPasswordError()).isNull();
  }

  @Test public void onLoginRequested_withInvalidPassword_shouldUpdateErrorState() {
    when(login.execute(anyString(), anyString())).thenReturn(
        Completable.error(new InvalidPasswordException()));
    when(resourceProvider.getString(anyInt())).thenReturn("Error");

    loginViewModel.getObservableLoginState().observeForever(stateObserver);
    loginViewModel.onLoginRequested();

    verify(stateObserver, times(3)).onChanged(stateArgumentCaptor.capture());

    assertThat(stateArgumentCaptor.getValue().getUsernameError()).isNull();
    assertThat(stateArgumentCaptor.getValue().getPasswordError()).isEqualTo("Error");
  }
}