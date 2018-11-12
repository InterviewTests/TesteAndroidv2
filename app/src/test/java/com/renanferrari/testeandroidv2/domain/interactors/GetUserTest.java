package com.renanferrari.testeandroidv2.domain.interactors;

import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.renanferrari.testeandroidv2.common.Constants.VALID_USER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class GetUserTest {

  @Mock private UserManager userManager;

  private GetUser getUser;

  @Before public void setUp() {
    getUser = new GetUser(userManager);
  }

  @Test public void execute_withNonNullUser_shouldReturnUserAndComplete() {
    when(userManager.getUser()).thenReturn(VALID_USER);

    final TestObserver<User> testObserver = getUser.execute().test();

    testObserver.awaitTerminalEvent();
    testObserver.assertComplete();
    testObserver.assertValue(VALID_USER);
    testObserver.assertNoErrors();

    verify(userManager).getUser();
  }

  @Test public void execute_withNullUser_shouldComplete() {
    when(userManager.getUser()).thenReturn(null);

    final TestObserver<User> testObserver = getUser.execute().test();

    testObserver.awaitTerminalEvent();
    testObserver.assertComplete();
    testObserver.assertNoValues();
    testObserver.assertNoErrors();

    verify(userManager).getUser();
  }
}