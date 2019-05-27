package com.caique.everis.testeandroid;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.caique.everis.testeandroid.model.AccountInfo;
import com.caique.everis.testeandroid.model.AccountInfoResponse;
import com.caique.everis.testeandroid.model.ClientData;
import com.caique.everis.testeandroid.model.Login;
import com.caique.everis.testeandroid.model.LoginResponse;
import com.caique.everis.testeandroid.viewmodel.BankViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class BankViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private BankViewModel vm;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vm = mock(BankViewModel.class);
    }

    @Test
    public void getUserData() {
        MutableLiveData<LoginResponse> mutableLiveData = new MutableLiveData();
        mutableLiveData.setValue(loginResponse());
        when(vm.getLoginResponse(login())).thenReturn(mutableLiveData);

        assertEquals("Nome Teste", mutableLiveData.getValue().getClientData().getName());
    }

    @Test
    public void getAccountResponse() {
        MutableLiveData<AccountInfoResponse> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(accountInfoResponse());
        when(vm.getAccountInfoResponse()).thenReturn(mutableLiveData);

        assertEquals("Pagamento", mutableLiveData.getValue().getAccountInfos().get(0).getTitle());
    }

    @Test
    public void invalidPassword() {
        assertEquals(false, BankViewModel.isValidPassword("aaaa"));
        assertEquals(false, BankViewModel.isValidPassword("1234"));
        assertEquals(false, BankViewModel.isValidPassword("ASDW"));
        assertEquals(false, BankViewModel.isValidPassword("!@#$"));
        assertEquals(false, BankViewModel.isValidPassword(""));
    }

    @Test
    public void isValidPassword() {
        assertEquals(true, BankViewModel.isValidPassword("a2@A"));
    }

    public Login login() {
        Login login = new Login();
        login.setUser("Teste");
        login.setPassword("a2@A");
        return login;
    }

    public LoginResponse loginResponse() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setClientData(new ClientData());
        loginResponse.getClientData().setAgency("123");
        loginResponse.getClientData().setBalance(1.000);
        loginResponse.getClientData().setBankAccount("12345");
        loginResponse.getClientData().setName("Nome Teste");
        loginResponse.getClientData().setUserId(1);
        return loginResponse;
    }

    public AccountInfoResponse accountInfoResponse() {
        AccountInfoResponse accountInfoResponse = new AccountInfoResponse();
        accountInfoResponse.setAccountInfos(new ArrayList<AccountInfo>());
        accountInfoResponse.getAccountInfos().add(new AccountInfo());
        accountInfoResponse.getAccountInfos().get(0).setTitle("Pagamento");
        accountInfoResponse.getAccountInfos().get(0).setDate("01/01/2000");
        accountInfoResponse.getAccountInfos().get(0).setDesc("Conta de Luz");
        accountInfoResponse.getAccountInfos().get(0).setValue(100.0);
        return accountInfoResponse;
    }
}
