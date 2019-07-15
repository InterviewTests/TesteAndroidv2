package br.com.fernandodutra.testeandroidv2.activities.login;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.net.ContentHandler;
import java.sql.SQLException;

import br.com.fernandodutra.testeandroidv2.models.UserAccount;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 30/06/2019
 * Time: 00:53
 * TesteAndroidv2_CleanCode
 */
@RunWith(RobolectricTestRunner.class)
public class LoginSQliteWorkerTest {

    private Context context;
    private LoginSQliteWorker loginSQliteWorker;
    private UserAccount userAccount;
    private int userId;

    @Before
    public void setUp() throws Exception {
        this.context = Robolectric.setupActivity(LoginActivity.class);

        loginSQliteWorker = new LoginSQliteWorker(context);

        userId = loginSQliteWorker.nextID();
        userAccount = new UserAccount(userId, "Fernando", "123456-6", "1234", 1000.0d);
    }

    @Test
    public void insert_isValid() {
        boolean valid = false;
        loginSQliteWorker.insert(userAccount);
        valid = true;
        Assert.assertTrue(valid);
    }

    @Test
    public void update_isValid() {
        boolean valid = false;
        loginSQliteWorker.insert(userAccount);
        loginSQliteWorker.update(userAccount);
        valid = true;
        Assert.assertTrue(valid);
    }

    @Test
    public void delete_isValid() {
        boolean valid = false;
        loginSQliteWorker.insert(userAccount);
        loginSQliteWorker.delete(userAccount);
        valid = true;
        Assert.assertTrue(valid);
    }

    @Test
    public void findById_isValid() {
        UserAccount userAccountValid = null;
        loginSQliteWorker.insert(userAccount);
        userAccountValid = loginSQliteWorker.findById(userId);
        Assert.assertNotNull(userAccountValid);
    }

    @Test
    public void nextID_isValid() {
        int nextID = loginSQliteWorker.nextID();
        Assert.assertTrue( nextID > 0);
    }


}