package br.com.fernandodutra.testeandroidv2.activities.statementList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import br.com.fernandodutra.testeandroidv2.models.UserAccount;


import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 30/06/2019
 * Time: 03:14
 * TesteAndroidv2_CleanCode
 */

@RunWith(RobolectricTestRunner.class)
public class StatementListInteractorTest {

    @Test
    public void statementList_isValid() {
        //Given
        UserAccount userAccount = new UserAccount(1, "Fernando", "1234", "65432-1", 1000.00d);
        StatementListInteractor statementListInteractor = new StatementListInteractor();

        //When
        statementListInteractor.fetchStatementListMetaData(userAccount.getUserId());

        // Then
        assertEquals(0, statementListInteractor.statementListResponse.errorMessage.size());
    }

    @Test
    public void statementList_isNotValid() {
        //Given
        UserAccount userAccount = new UserAccount();
        StatementListInteractor statementListInteractor = new StatementListInteractor();

        //When
        statementListInteractor.fetchStatementListMetaData(userAccount.getUserId());

        // Then
        assertEquals(0, statementListInteractor.statementListResponse.errorMessage.size());
    }
}