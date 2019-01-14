package com.resourceit.app;


import android.text.BoringLayout;

import com.resourceit.app.views.LoginFragment;
import com.resourceit.app.views.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.FragmentTestUtil;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


@RunWith(RobolectricTestRunner.class)
public class MainActivityUnitTest {

    MainActivity mainActivity;
    LoginFragment loginFragment;

    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        loginFragment = new LoginFragment();
        startFragment(loginFragment);
    }

    private void startFragment( Fragment fragment ) {
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null );
        fragmentTransaction.commit();
    }

    @Test
    public void MainActivity_ShouldNOT_be_Null(){
        Assert.assertNotNull(mainActivity);
    }

    @Test
    public void LoginFragment_ShouldNOT_be_Null(){
        Assert.assertNotNull(loginFragment);
    }

    @Test
    public void validateUser_Shoud_be_False(){
        Boolean res = loginFragment.validateUser("fds");
        Assert.assertFalse(res);
    }

    @Test
    public void validateUser_email_Shoud_be_True(){
        Boolean res = loginFragment.validateUser("khalid@teste.test");
        Assert.assertTrue(res);
    }

    @Test
    public void validateUser_CPF_Shoud_be_True(){
        Boolean res = loginFragment.validateUser("25528921082");
        Assert.assertTrue(res);
    }

    @Test
    public void validatePassword_Shoud_be_True(){
        Boolean res = loginFragment.validatePassword("Aa123!@A");
        Assert.assertTrue(res);
    }

    @Test
    public void validatePassword_Shoud_be_False(){
        Boolean res = loginFragment.validatePassword("a12as12ad");
        Assert.assertFalse(res);
    }

}
