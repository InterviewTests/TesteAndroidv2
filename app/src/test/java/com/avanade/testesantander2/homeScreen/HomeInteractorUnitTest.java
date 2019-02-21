package com.avanade.testesantander2.homeScreen;


import androidx.annotation.NonNull;

import com.avanade.testesantander2.CurrencyResponse;
import com.avanade.testesantander2.Erro;
import com.avanade.testesantander2.Statement;
import com.avanade.testesantander2.UserAccount;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
public class HomeInteractorUnitTest {

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
    private String getJsonString() {
        return "{\"statementList\":[{\"title\":\"Pagamento\",\"desc\":\"Conta de luz\",\"date\":\"2018-08-15\",\"value\":-50},{\"title\":\"TED Recebida\",\"desc\":\"Joao Alfredo\",\"date\":\"2018-07-25\",\"value\":745.03},{\"title\":\"DOC Recebida\",\"desc\":\"Victor Silva\",\"date\":\"2018-06-23\",\"value\":399.9},{\"title\":\"Pagamento\",\"desc\":\"Conta de internet\",\"date\":\"2018-05-12\",\"value\":-73.4},{\"title\":\"Pagamento\",\"desc\":\"Faculdade\",\"date\":\"2018-09-10\",\"value\":-500},{\"title\":\"Pagamento\",\"desc\":\"Conta de telefone\",\"date\":\"2018-10-17\",\"value\":-760},{\"title\":\"TED Enviada\",\"desc\":\"Roberto da Luz\",\"date\":\"2018-07-27\",\"value\":-35.67},{\"title\":\"Pagamento\",\"desc\":\"Boleto\",\"date\":\"2018-08-01\",\"value\":-200},{\"title\":\"TED Recebida\",\"desc\":\"Salário\",\"date\":\"2018-08-21\",\"value\":1400.5}],\"error\":{}}";
    }

    @NonNull
    private ArrayList<Statement> getStatementList() {
        Gson gson = new Gson();
        CurrencyResponse currencyResponse = gson.fromJson(getJsonString(), CurrencyResponse.class);
        return currencyResponse.getStatementList();
    }

    @NonNull
    private HomeViewModel getHomeViewModel() {
        HomeViewModel homeViewModel = new HomeViewModel();
        homeViewModel.userAccount = getUserAccount();
        homeViewModel.currencyAccount = getStatementList();
        return homeViewModel;
    }

    @NonNull
    private Erro getErro() {
        Erro erro = new Erro(17, "Testing ----------------- getErro");
        return erro;
    }

    @Test
    public void apresentarUsuario_para_Activity() {
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        HomePresenterInputSpy homePresenterInputSpy = new HomePresenterInputSpy();
        homeInteractor.output = homePresenterInputSpy;

        homeInteractor.homeViewModel = getHomeViewModel();

        // When
        homeInteractor.showUser();

        //Then
        Assert.assertTrue(homePresenterInputSpy.callUsuario);
        Assert.assertNotNull(homePresenterInputSpy.userAccountCopy);
        Assert.assertTrue(getUserAccount().toString().equalsIgnoreCase(homePresenterInputSpy.userAccountCopy.toString()));
    }


    @Test
    public void getData_and_sendToPresenter() {
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        HomePresenterInputSpy homePresenterInputSpy = new HomePresenterInputSpy();
        homeInteractor.output = homePresenterInputSpy;

        homeInteractor.homeViewModel = getHomeViewModel();

        // When
        homeInteractor.showDados();

        //Then
        Assert.assertTrue(homePresenterInputSpy.callDados);
        Assert.assertTrue(homePresenterInputSpy.homeViewModelCopy.userAccount.toString().equalsIgnoreCase(getUserAccount().toString()));
        Assert.assertNotNull(homePresenterInputSpy.homeViewModelCopy.currencyAccount);

    }


    @Test
    public void apresentarErro_when_getData_Failure() {
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        HomePresenterInputSpy homePresenterInputSpy = new HomePresenterInputSpy();
        homeInteractor.output = homePresenterInputSpy;

        homeInteractor.erro = getErro();

        // When
        homeInteractor.showErro();

        //Then
        Assert.assertTrue(homePresenterInputSpy.callErros);
        Assert.assertTrue(homePresenterInputSpy.erroCopy.equalsIgnoreCase(getErro().toString()));

    }

    private class HomePresenterInputSpy implements HomePresenterInput {

        boolean callUsuario = false;
        boolean callDados = false;
        boolean callErros = false;

        HomeViewModel homeViewModelCopy;
        UserAccount userAccountCopy;
        String erroCopy;


        @Override
        public void apresentarUsuario(UserAccount userAccount) {
            callUsuario = true;
            userAccountCopy = userAccount;
        }

        @Override
        public void apresentarDados(HomeViewModel homeViewModel) {
            callDados = true;
            homeViewModelCopy = homeViewModel;

        }

        @Override
        public void apresentarErro(String erro) {
            callErros = true;
            erroCopy = erro;
        }
    }


}
