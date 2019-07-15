package br.com.fernandodutra.testeandroidv2.activities.dao;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.sql.SQLException;

import br.com.fernandodutra.testeandroidv2.activities.login.LoginActivity;
import br.com.fernandodutra.testeandroidv2.dao.UserAccountDAO;
import br.com.fernandodutra.testeandroidv2.dao.UserAccountDAOLiter;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 29/06/2019
 * Time: 12:57
 * TesteAndroidv2_CleanCode
 */

@RunWith(RobolectricTestRunner.class)
public class UserAccountDAOLiterTest {

    private Context context;
    private UserAccountDAO<UserAccount> userAccountDAO;
    private UserAccount userAccount;
    private int userId;

    @Before
    public void setUp() throws Exception {
        this.context = Robolectric.setupActivity(LoginActivity.class);;
        userAccountDAO = new UserAccountDAOLiter(context);
        userId = userAccountDAO.nextID();
        userAccount = new UserAccount(userId, "Fernando", "123456-6", "1234", 1000.0d);
    }

    @Test
    public void insert_isValid() {
        boolean valid = false;
        try {
            userAccountDAO.insert(userAccount);
            valid = true;
        } catch (SQLException e) {
            valid = false;
            e.printStackTrace();
        }

        Assert.assertTrue(valid);
    }

    @Test
    public void update_isValid() {
        boolean valid = false;
        try {
            userAccountDAO.update(userAccount);
            valid = true;
        } catch (SQLException e) {
            valid = false;
            e.printStackTrace();
        }

        Assert.assertTrue(valid);
    }

    @Test
    public void delete_isValid() {
        boolean valid = false;
        try {
            userAccountDAO.delete(userId);
            valid = true;
        } catch (SQLException e) {
            valid = false;
            e.printStackTrace();
        }

        Assert.assertTrue(valid);
    }

    @Test
    public void findByID_isValid() {
        UserAccount userAccountValid = null;
        try {
            userAccountDAO.insert(userAccount);
            userAccountValid = userAccountDAO.findByID(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(userAccountValid);
    }
}