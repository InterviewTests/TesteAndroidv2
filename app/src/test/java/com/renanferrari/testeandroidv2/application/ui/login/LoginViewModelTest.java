package com.renanferrari.testeandroidv2.application.ui.login;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import com.renanferrari.testeandroidv2.application.common.providers.SchedulerProvider;
import com.renanferrari.testeandroidv2.domain.interactors.Login;
import io.reactivex.Completable;
import java.util.ArrayList;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class LoginViewModelTest {

  @Mock private Login login;
  @Mock private Observer<LoginState> stateObserver;

  @Captor private ArgumentCaptor<LoginState> stateArgumentCaptor;

  @Rule public TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  private LoginViewModel loginViewModel;

  @Before public void setUp() {
    loginViewModel = new LoginViewModel(login, SchedulerProvider.forTesting());
  }

  @Test public void onLoginRequested() {
    when(login.execute(anyString(), anyString())).thenReturn(Completable.complete());

    final List<LoginState> allStates = new ArrayList<>();

    loginViewModel.getObservableState()
        .observeForever(loginState -> allStates.add(LoginState.copyOf(loginState)));
    //loginViewModel.getObservableState().observeForever(stateObserver);
    loginViewModel.onLoginRequested();

    final LoginState expectedInitialState = LoginState.createDefault();

    final LoginState expectedLoadingState = LoginState.createDefault();
    expectedLoadingState.setLoading(true);

    final LoginState expectedFinalState = LoginState.createDefault();
    expectedFinalState.setLoading(false);
    expectedFinalState.setLoggedIn(true);

    //verify(stateObserver, times(3)).onChanged(stateArgumentCaptor.capture());
    //final List<LoginState> allStates = stateArgumentCaptor.getAllValues();
    assertThat(allStates.get(0)).named("initial state").isEqualTo(expectedInitialState);
    assertThat(allStates.get(1)).named("loading state").isEqualTo(expectedLoadingState);
    assertThat(allStates.get(2)).named("final state").isEqualTo(expectedFinalState);
  }
}