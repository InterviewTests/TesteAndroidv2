package com.renanferrari.testeandroidv2.domain.interactors;

import com.renanferrari.testeandroidv2.common.Optional;
import com.renanferrari.testeandroidv2.domain.model.statements.Statement;
import com.renanferrari.testeandroidv2.domain.model.statements.StatementsRepository;
import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.renanferrari.testeandroidv2.common.Constants.VALID_STATEMENT_LIST;
import static com.renanferrari.testeandroidv2.common.Constants.VALID_USER;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class GetStatementsTest {

  @Mock private StatementsRepository statementsRepository;
  @Mock private UserManager userManager;

  private GetStatements getStatements;

  @Before public void setUp() {
    getStatements = new GetStatements(statementsRepository, userManager);
  }

  @Test public void execute_withLoggedInUser_shouldReturnStatements() {
    when(userManager.getUserObservable())
        .thenReturn(Observable.just(Optional.of(VALID_USER)).concatWith(Observable.never()));
    when(statementsRepository.getRecentStatements(anyInt()))
        .thenReturn(Single.just(VALID_STATEMENT_LIST));

    final TestObserver<List<Statement>> testObserver = getStatements.execute().test();

    testObserver.awaitTerminalEvent();
    testObserver.assertComplete();
    testObserver.assertValue(VALID_STATEMENT_LIST);
    testObserver.assertNoErrors();

    verify(userManager).getUserObservable();
    verify(statementsRepository).getRecentStatements(VALID_USER.getUserId());
  }

  @Test public void execute_withNoUser_shouldNotComplete() {
    when(userManager.getUserObservable())
        .thenReturn(Observable.just(Optional.<User>absent()).concatWith(Observable.never()));

    final TestObserver<List<Statement>> testObserver = getStatements.execute().test();

    testObserver.assertNotTerminated();
    testObserver.assertNotComplete();
    testObserver.assertNoValues();
    testObserver.assertNoErrors();

    verify(userManager).getUserObservable();
    verifyZeroInteractions(statementsRepository);
  }
}