package com.resource.bankapplication.domain;

import com.resource.bankapplication.config.BaseCallback;
import com.resource.bankapplication.ui.login.BankLoginContract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserAccountTest {


    @Mock
    private BankLoginContract.View view;
    @Mock
    private UserAccountContract.IRepository repository;
    @Mock
    private UserAccount userAccount;

    @Captor
    private ArgumentCaptor<BaseCallback<UserAccount>> loadUserCallback;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginDomain() {
        userAccount.repository = this.repository;
        Mockito.when(userAccount.getUsername()).thenReturn("test@test.teste");
        Mockito.when(userAccount.getPassword()).thenReturn("Test@1");
        Assert.assertEquals(userAccount.getUsername(), "test@test.teste");
        Assert.assertEquals(userAccount.getPassword(), "Test@1");
        userAccount.login(loadUserCallback.capture());
        Mockito.verify(userAccount).login(loadUserCallback.capture());
    }

    @Test
    public void loadPreference() {
        userAccount.repository = this.repository;
        userAccount.loadPreference(Mockito.eq(view.getContext()), loadUserCallback.capture());
        Mockito.verify(userAccount).loadPreference(Mockito.eq(view.getContext()), loadUserCallback.capture());
    }


    @Test(expected = Exception.class)
    public void testRepositoryNull(){
        userAccount = new UserAccount("test@test.teste", "TEST@1");
        userAccount.login(null);
    }@Test(expected = Exception.class)
    public void testFieldPasswordLowerCase(){
        userAccount = new UserAccount("test@test.teste", "TEST@1");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldPasswordUpCase() {
        userAccount = new UserAccount("test@test.teste", "test@1");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldPasswordNumber() {
        userAccount = new UserAccount("test@test.teste", "test@1");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldPasswordSpecial() {
        userAccount = new UserAccount("test@test.teste", "testE1");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldPasswordLength() {
        userAccount = new UserAccount("test@test.teste", "tes");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test
    public void testFieldLoginOk() {
        userAccount = new UserAccount("test@test.teste", "Test@1");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldUsernameAt() {
        userAccount = new UserAccount("testtest.teste", "Test@1");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldUsernameDot() {
        userAccount = new UserAccount("test@testteste", "Test@1");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldUsernameEmpty() {
        userAccount = new UserAccount("", "Test@1");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldUsernameSpace() {
        userAccount = new UserAccount("  ", "Test@1");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldPasswordEmpty() {
        userAccount = new UserAccount("test@test.teste", "");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
    @Test(expected = Exception.class)
    public void testFieldPasswordSpace() {
        userAccount = new UserAccount("test@test.teste", "  ");
        userAccount.repository = this.repository;
        userAccount.login(null);
    }
}