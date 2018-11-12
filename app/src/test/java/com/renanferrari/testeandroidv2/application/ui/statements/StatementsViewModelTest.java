package com.renanferrari.testeandroidv2.application.ui.statements;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import com.renanferrari.testeandroidv2.application.common.providers.SchedulerProvider;
import com.renanferrari.testeandroidv2.application.common.utils.AccountFormatter;
import com.renanferrari.testeandroidv2.application.common.utils.DateFormatter;
import com.renanferrari.testeandroidv2.application.common.utils.MoneyFormatter;
import com.renanferrari.testeandroidv2.domain.interactors.GetStatements;
import com.renanferrari.testeandroidv2.domain.interactors.GetUser;
import com.renanferrari.testeandroidv2.domain.interactors.Logout;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
import static com.renanferrari.testeandroidv2.common.Constants.VALID_STATEMENT_LIST;
import static com.renanferrari.testeandroidv2.common.Constants.VALID_USER;
import static com.renanferrari.testeandroidv2.common.Constants.VALID_USER_ACCOUNT;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class StatementsViewModelTest {

  @Mock private GetStatements getStatements;
  @Mock private GetUser getUser;
  @Mock private Logout logout;

  @Mock private Observer<UserState> userStateObserver;
  @Mock private Observer<StatementsState> statementsStateObserver;

  @Captor private ArgumentCaptor<UserState> userStateArgumentCaptor;
  @Captor private ArgumentCaptor<StatementsState> statementsStateArgumentCaptor;

  @Rule public TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  private MoneyFormatter moneyFormatter;
  private DateFormatter dateFormatter;
  private StatementsViewModel statementsViewModel;

  @Before public void setUp() {
    final Locale locale = new Locale("pt", "BR");
    moneyFormatter = new MoneyFormatter(locale);
    dateFormatter = new DateFormatter(locale);
    statementsViewModel = new StatementsViewModel(getStatements, getUser, logout, moneyFormatter,
        dateFormatter, SchedulerProvider.forTesting());
  }

  @Test public void onUserRequested_withLoggedInUser_shouldUpdateState() {
    when(getUser.execute()).thenReturn(Maybe.just(VALID_USER));

    statementsViewModel.getObservableUserState().observeForever(userStateObserver);
    statementsViewModel.onUserRequested();

    final UserState expectedFinalState = UserState.builder()
        .name(VALID_USER.getName())
        .account(AccountFormatter.format(VALID_USER.getBankAccount(), VALID_USER.getAgency()))
        .balance(moneyFormatter.format(VALID_USER.getBalance()))
        .build();

    verify(userStateObserver, times(1)).onChanged(userStateArgumentCaptor.capture());

    assertThat(userStateArgumentCaptor.getValue()).isEqualTo(expectedFinalState);
  }

  @Test public void onUserRequested_withNoUser_shouldNotUpdateState() {
    when(getUser.execute()).thenReturn(Maybe.empty());

    statementsViewModel.getObservableUserState().observeForever(userStateObserver);
    statementsViewModel.onUserRequested();

    verifyZeroInteractions(userStateObserver);
  }

  @Test public void onStatementsRequested() {
    when(getStatements.execute()).thenReturn(Single.just(VALID_STATEMENT_LIST));

    statementsViewModel.getObservableStatementsState().observeForever(statementsStateObserver);
    statementsViewModel.onStatementsRequested();

    final StatementsState expectedInitialState = StatementsState.createDefault();
    final StatementsState expectedLoadingState = expectedInitialState.withLoading(true);
    final StatementsState expectedFinalState = StatementsState.create(Arrays.asList(
        StatementItem.create(VALID_STATEMENT_LIST.get(0), moneyFormatter, dateFormatter),
        StatementItem.create(VALID_STATEMENT_LIST.get(1), moneyFormatter, dateFormatter),
        StatementItem.create(VALID_STATEMENT_LIST.get(2), moneyFormatter, dateFormatter)));

    verify(statementsStateObserver, times(3)).onChanged(statementsStateArgumentCaptor.capture());

    final List<StatementsState> allStates = statementsStateArgumentCaptor.getAllValues();

    assertThat(allStates.get(0)).named("initial state").isEqualTo(expectedInitialState);
    assertThat(allStates.get(1)).named("loading state").isEqualTo(expectedLoadingState);
    assertThat(allStates.get(2)).named("final state").isEqualTo(expectedFinalState);
  }
}