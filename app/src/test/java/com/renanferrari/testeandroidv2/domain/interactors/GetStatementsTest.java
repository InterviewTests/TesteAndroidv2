package com.renanferrari.testeandroidv2.domain.interactors;

import com.renanferrari.testeandroidv2.domain.model.statements.StatementsRepository;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class GetStatementsTest {

  @Mock private StatementsRepository statementsRepository;
  @Mock private UserManager userManager;

  private GetStatements getStatements;

  @Before public void setUp() {
    getStatements = new GetStatements(statementsRepository, userManager);
  }

  @Test public void execute() {
    // TODO
  }
}