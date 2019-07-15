package br.com.fernandodutra.testeandroidv2.activities.statementList;

import android.content.Intent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.io.Serializable;

import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.utils.Constants;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 30/06/2019
 * Time: 01:23
 * TesteAndroidv2_CleanCode
 */

@RunWith(RobolectricTestRunner.class)
public class StatementListActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void statementListActivity_activityNotNull() {
        //Given
        UserAccount userAccount = new UserAccount(1, "Fernando", "1234", "65432-1", 1000.00d);

        Intent statementListIntent = new Intent();
        statementListIntent.putExtra(Constants.USERACCOUNT_USERID, (Serializable) userAccount);
        StatementListActivity statementList = Robolectric.buildActivity(StatementListActivity.class, statementListIntent).create().get();

        //When

        // Then
        Assert.assertNotNull(statementList);
    }

    @Test
    public void statementListActivity_fetchMetaDataIsValid() {
        //Given
        UserAccount userAccount = new UserAccount(1, "Fernando", "1234", "65432-1", 1000.00d);

        Intent statementListIntent = new Intent();
        statementListIntent.putExtra(Constants.USERACCOUNT_USERID, (Serializable) userAccount);
        StatementListActivity statementListActivity = Robolectric.buildActivity(StatementListActivity.class, statementListIntent).create().get();

        StatementListActivityOutputSpy statementListActivityOutputSpy = new StatementListActivityOutputSpy();
        statementListActivity.statementListInteractorInput = statementListActivityOutputSpy;

        //When
        statementListActivity.statementListInteractorInput.fetchStatementListMetaData(userAccount.getUserId());

        //Then
        Assert.assertTrue(statementListActivityOutputSpy.fetchStatementListMetaDataIsCalled);
    }

    private class StatementListActivityOutputSpy implements StatementListInteractorInput {

        boolean fetchStatementListMetaDataIsCalled = false;

        @Override
        public void fetchStatementListMetaData(Integer userId) {
            fetchStatementListMetaDataIsCalled = true;
        }
    }
}