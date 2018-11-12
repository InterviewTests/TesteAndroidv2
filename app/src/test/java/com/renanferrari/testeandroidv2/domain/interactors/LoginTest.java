package com.renanferrari.testeandroidv2.domain.interactors;

import com.renanferrari.testeandroidv2.domain.model.auth.AuthService;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidPasswordException;
import com.renanferrari.testeandroidv2.domain.model.auth.InvalidUsernameException;
import io.reactivex.Completable;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.renanferrari.testeandroidv2.common.Constants.INVALID_CPF;
import static com.renanferrari.testeandroidv2.common.Constants.INVALID_EMAIL;
import static com.renanferrari.testeandroidv2.common.Constants.INVALID_PASSWORD_NO_CAPITAL_LETTER;
import static com.renanferrari.testeandroidv2.common.Constants.INVALID_PASSWORD_NO_SPECIAL_CHARACTER;
import static com.renanferrari.testeandroidv2.common.Constants.VALID_CPF;
import static com.renanferrari.testeandroidv2.common.Constants.VALID_EMAIL;
import static com.renanferrari.testeandroidv2.common.Constants.VALID_PASSWORD;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class LoginTest {

  @Mock private AuthService authService;

  private Login login;

  @Before public void setUp() {
    login = new Login(authService);
  }

  @Test public void execute_withValidEmailAndPassword_shouldComplete() {
    when(authService.signIn(anyString(), anyString())).thenReturn(Completable.complete());

    final TestObserver<Void> testObserver = login.execute(VALID_EMAIL, VALID_PASSWORD).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertComplete();
    testObserver.assertNoValues();
    testObserver.assertNoErrors();

    verify(authService).signIn(anyString(), anyString());
  }

  @Test public void execute_withValidCpfAndPassword_shouldComplete() {
    when(authService.signIn(anyString(), anyString())).thenReturn(Completable.complete());

    final TestObserver<Void> testObserver = login.execute(VALID_CPF, VALID_PASSWORD).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertComplete();
    testObserver.assertNoValues();
    testObserver.assertNoErrors();

    verify(authService).signIn(anyString(), anyString());
  }

  @Test public void execute_withEmptyUsername_shouldFail() {
    final TestObserver<Void> testObserver = login.execute("", VALID_PASSWORD).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNotComplete();
    testObserver.assertError(InvalidUsernameException.class);

    verify(authService, never()).signIn(anyString(), anyString());
  }

  @Test public void execute_withInvalidEmail_shouldFail() {
    final TestObserver<Void> testObserver = login.execute(INVALID_EMAIL, VALID_PASSWORD).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNotComplete();
    testObserver.assertError(InvalidUsernameException.class);

    verify(authService, never()).signIn(anyString(), anyString());
  }

  @Test public void execute_withInvalidCpf_shouldFail() {
    final TestObserver<Void> testObserver = login.execute(INVALID_CPF, VALID_PASSWORD).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNotComplete();
    testObserver.assertError(InvalidUsernameException.class);

    verify(authService, never()).signIn(anyString(), anyString());
  }

  @Test public void execute_withEmptyPassword_shouldFail() {
    final TestObserver<Void> testObserver = login.execute(VALID_EMAIL, "").test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNotComplete();
    testObserver.assertError(InvalidPasswordException.class);

    verify(authService, never()).signIn(anyString(), anyString());
  }

  @Test public void execute_withInvalidPasswordNoCapitalLetter_shouldFail() {
    final TestObserver<Void> testObserver =
        login.execute(VALID_EMAIL, INVALID_PASSWORD_NO_CAPITAL_LETTER).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNotComplete();
    testObserver.assertError(InvalidPasswordException.class);

    verify(authService, never()).signIn(anyString(), anyString());
  }

  @Test public void execute_withInvalidPasswordNoSpecialCharacter_shouldFail() {
    final TestObserver<Void> testObserver =
        login.execute(VALID_EMAIL, INVALID_PASSWORD_NO_SPECIAL_CHARACTER).test();

    testObserver.awaitTerminalEvent();
    testObserver.assertNotComplete();
    testObserver.assertError(InvalidPasswordException.class);

    verify(authService, never()).signIn(anyString(), anyString());
  }
}