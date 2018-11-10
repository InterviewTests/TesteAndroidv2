package com.renanferrari.testeandroidv2.domain.model;

import com.renanferrari.testeandroidv2.domain.model.auth.AuthApi;
import com.renanferrari.testeandroidv2.domain.model.auth.AuthService;
import com.renanferrari.testeandroidv2.domain.model.auth.ServerException;
import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static com.renanferrari.testeandroidv2.domain.common.Constants.RUNTIME_EXCEPTION;
import static com.renanferrari.testeandroidv2.domain.common.Constants.VALID_EMAIL;
import static com.renanferrari.testeandroidv2.domain.common.Constants.VALID_PASSWORD;
import static com.renanferrari.testeandroidv2.domain.common.Constants.VALID_USER;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class AuthServiceTest {

  @Mock private AuthApi authApi;
  @Mock private UserManager userManager;

  @Captor private ArgumentCaptor<User> userArgumentCaptor;

  private AuthService authService;

  @Before public void setUp() {
    authService = new AuthService(authApi, userManager);
  }

  @Test public void signIn_withSuccessfulApiLogin_shouldCallSetUser() {
    when(authApi.login(anyString(), anyString())).thenReturn(Single.just(VALID_USER));

    final TestObserver<Void> testObserver = authService.signIn(VALID_EMAIL, VALID_PASSWORD).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertComplete();
    testObserver.assertNoValues();
    testObserver.assertNoErrors();

    verify(authApi).login(anyString(), anyString());
    verify(userManager).setUser(userArgumentCaptor.capture());

    assertThat(VALID_USER).isEqualTo(userArgumentCaptor.getValue());
  }

  @Test public void signIn_withFailedApiLogin_shouldWrapException() {
    when(authApi.login(anyString(), anyString())).thenReturn(Single.error(RUNTIME_EXCEPTION));

    final TestObserver<Void> testObserver = authService.signIn(VALID_EMAIL, VALID_PASSWORD).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNotComplete();
    testObserver.assertError(ServerException.class);

    verify(authApi).login(anyString(), anyString());
    verify(userManager, never()).setUser(any());
  }
}