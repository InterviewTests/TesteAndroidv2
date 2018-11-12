package com.renanferrari.testeandroidv2.application.ui.statements;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.renanferrari.testeandroidv2.application.common.providers.SchedulerProvider;
import com.renanferrari.testeandroidv2.application.common.utils.DateFormatter;
import com.renanferrari.testeandroidv2.application.common.utils.MoneyFormatter;
import com.renanferrari.testeandroidv2.domain.interactors.GetStatements;
import com.renanferrari.testeandroidv2.domain.interactors.GetUser;
import com.renanferrari.testeandroidv2.domain.interactors.Logout;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class) public class StatementsViewModelTest {

  @Mock GetStatements getStatements;
  @Mock GetUser getUser;
  @Mock Logout logout;
  @Mock MoneyFormatter moneyFormatter;
  @Mock DateFormatter dateFormatter;

  @Rule public TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  private StatementsViewModel statementsViewModel;

  @Before public void setUp() {
    statementsViewModel = new StatementsViewModel(getStatements, getUser, logout, moneyFormatter,
        dateFormatter, SchedulerProvider.forTesting());
  }

  @Test public void onUserRequested() {
    // TODO
  }

  @Test public void onStatementsRequested() {
    // TODO
  }

  @Test public void onLogoutRequested() {
    // TODO
  }
}