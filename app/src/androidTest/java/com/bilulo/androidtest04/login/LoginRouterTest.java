package com.bilulo.androidtest04.login;


import androidx.test.rule.ActivityTestRule;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.ui.list.view.ListActivity;
import com.bilulo.androidtest04.ui.login.router.LoginRouter;
import com.bilulo.androidtest04.ui.login.view.LoginActivity;
import com.bilulo.androidtest04.utils.SharedPreferencesUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;

public class LoginRouterTest {

    private UserAccountModel userAccountModel;

    @Rule
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setTestVariables() {
        userAccountModel = new UserAccountModel();
        userAccountModel.setAgency("012314588");
        userAccountModel.setBalance(BigDecimal.valueOf(3123.33455));
        userAccountModel.setName("Pedro Alvares Cabral");
        userAccountModel.setUserId(2);
        userAccountModel.setBankAccount("3333");
    }

    @Test
    public void loginRouter_determineNextScreen() {
        LoginRouter loginRouter = new LoginRouter();
        LoginActivity activity = rule.getActivity();
        loginRouter.activity = new WeakReference<>(activity);
        activity.router = loginRouter;
        String targetActivityName = loginRouter.getListActivityIntent(userAccountModel).getComponent().getClassName();
        Assert.assertEquals(targetActivityName, ListActivity.class.getName());
    }

    @Test
    public void loginRouter_saveUsernameInSharedPreferences() {
        LoginRouter loginRouter = new LoginRouter();
        LoginActivity activity = rule.getActivity();
        loginRouter.activity = new WeakReference<>(activity);
        activity.router = loginRouter;
        loginRouter.saveUsernameInSharedPreferences("a@a.com");
        Assert.assertNotNull(SharedPreferencesUtil.getString(activity, LoginActivity.KEY_USER, null));
    }
}
