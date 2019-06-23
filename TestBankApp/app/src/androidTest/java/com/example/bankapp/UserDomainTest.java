package com.example.bankapp;

import android.support.test.runner.AndroidJUnit4;

import com.example.bankapp.domain.UserDomain;
import com.example.bankapp.repository.LoginRepository;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserDomainTest extends TestCase {

    @Test(expected = Exception.class)
    public void testFieldsIsEmpty() throws Exception {
        UserDomain userDomain = new UserDomain("","",null);
        userDomain.repository = new LoginRepository();
        userDomain.login(null);
    }

    @Test(expected = Exception.class)
    public void testFieldPasswordWithoutEspecialCharactere() throws Exception {
        UserDomain userDomain = new UserDomain("teste","test1T",null);
        userDomain.repository = new LoginRepository();
        userDomain.login(null);
    }

    @Test(expected = Exception.class)
    public void testFieldPasswordWithoutUpperCharactere() throws Exception {
        UserDomain userDomain = new UserDomain("teste","test1@",null);
        userDomain.repository = new LoginRepository();
        userDomain.login(null);
    }

    @Test(expected = Exception.class)
    public void testFieldPasswordWithoutLowerCharactere() throws Exception {
        UserDomain userDomain = new UserDomain("teste","TTT1@",null);
        userDomain.repository = new LoginRepository();
        userDomain.login(null);
    }

    @Test(expected = Exception.class)
    public void testFieldPasswordWithoutNumber() throws Exception {
        UserDomain userDomain = new UserDomain("teste","test@T",null);
        userDomain.repository = new LoginRepository();
        userDomain.login(null);
    }
}
