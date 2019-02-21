package com.avanade.testesantander2.homeScreen;

import android.content.Intent;
import androidx.annotation.NonNull;

import com.avanade.testesantander2.UserAccount;
import com.avanade.testesantander2.loginScreen.LoginRouter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class HomeActivityUnitTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @NonNull
    private UserAccount getUserAccount() {
        UserAccount u = new UserAccount(1, "Teste", "070707", "0707", -17.33);
        return u;
    }
    @NonNull
    private Intent getIntent(){
        Intent intent = new Intent();
        intent.putExtra(LoginRouter.CHAVE, getUserAccount());
        return intent;
    }

    @Test
    public void HomeActivity_ShouldNOT_be_Null() {
        //Given
        HomeActivity homeActivity =
                Robolectric.buildActivity(HomeActivity.class, getIntent())
                        .create()
                        .get();

        //When

        // Then
        Assert.assertNotNull(homeActivity);
    }

    @Test
    public void onCreate_shouldCall_buscarDados() {
        //Given
        HomeActivity homeActivity =
                Robolectric.buildActivity(HomeActivity.class, getIntent())
                        .create()
                        .get();

        HomeActivityOutputSpy homeActivityOutputSpy = new HomeActivityOutputSpy();
        homeActivity.output = homeActivityOutputSpy;

        //When
        homeActivity.buscarDados();

        //Then
        Assert.assertNotNull(homeActivity);
        Assert.assertTrue(homeActivityOutputSpy.callGetData);
        Assert.assertTrue(getUserAccount().toString().equalsIgnoreCase(homeActivityOutputSpy.userAccountCopy.toString()));
    }


    // SPY CLASS -> para verificar calls
    private class HomeActivityOutputSpy implements HomeInteractorInput {

        boolean callGetData = false;
        UserAccount userAccountCopy;

        @Override
        public void getData(UserAccount userAccount) {
            callGetData = true;
            userAccountCopy = userAccount;
        }
    }


}