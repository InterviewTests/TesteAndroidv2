package br.com.fernandodutra.testeandroidv2.activities.login;

import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

import br.com.fernandodutra.testeandroidv2.activities.statementList.StatementListActivity;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 29/06/2019
 * Time: 12:27
 * TesteAndroidv2_CleanCode
 */
@RunWith(RobolectricTestRunner.class)
public class LoginRouterTest {

    @Test
    public void createIntent_isValid() {
        //Given
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);

        //When
        Intent intent = loginActivity.loginRouter.createIntent(new UserAccount(1, "Fernando", "1234", "65432-1", 1000.00d));

        //Then
        String targetActivityName = intent.getComponent().getClassName();
        Assert.assertEquals(targetActivityName, StatementListActivity.class.getName());
    }

}