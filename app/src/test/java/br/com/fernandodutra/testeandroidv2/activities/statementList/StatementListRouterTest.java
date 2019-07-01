package br.com.fernandodutra.testeandroidv2.activities.statementList;

import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.io.Serializable;
import java.lang.ref.WeakReference;

import br.com.fernandodutra.testeandroidv2.activities.login.LoginActivity;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.utils.Constants;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 30/06/2019
 * Time: 01:18
 * TesteAndroidv2_CleanCode
 */

@RunWith(RobolectricTestRunner.class)
public class StatementListRouterTest {

    @Test
    public void createIntent_isValid() {
        //Given
        UserAccount userAccount = new UserAccount(1, "Fernando", "1234", "65432-1", 1000.00d);

        Intent statementListIntent = new Intent();
        statementListIntent.putExtra(Constants.USERACCOUNT_USERID, (Serializable) userAccount);
        StatementListActivity statementList = Robolectric.buildActivity(StatementListActivity.class, statementListIntent).create().get();

        StatementListRouter statementListRouter = new StatementListRouter();
        statementList.statementListRouter = statementListRouter;
        statementListRouter.activity = new WeakReference<StatementListActivity>(statementList);

        //When
        Intent intent = statementListRouter.createIntent();

        //Then
        String targetActivityName = intent.getComponent().getClassName();
        Assert.assertEquals(targetActivityName, LoginActivity.class.getName());
    }

}