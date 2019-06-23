package com.example.bankapp;

import android.support.test.runner.AndroidJUnit4;

import com.example.bankapp.domain.UserDomain;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.user.UserAccountModel;
import com.example.bankapp.repository.LoginRepository;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RepositoryTest extends TestCase {

    @Test
    public void testRequestLogin() {
        UserDomain userDomain = new UserDomain("tes", "TES1@q", null);
        userDomain.repository = new LoginRepository();
        userDomain.repository.login("", "", new BaseCallback<UserAccountModel>() {
            @Override
            public void onSuccessful(UserAccountModel value) {
                Assert.assertEquals(new UserAccountModel(), value);
            }

            @Override
            public void onUnsuccessful(String text) {

            }
        });


    }

}
