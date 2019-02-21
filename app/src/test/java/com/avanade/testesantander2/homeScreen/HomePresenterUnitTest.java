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

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by mkaratadipalayam on 11/10/16.
 */
@RunWith(RobolectricTestRunner.class)
public class HomePresenterUnitTest {
    public static String TAG = HomePresenterUnitTest.class.getSimpleName();

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
    public void showUsuario_paraTopo() {
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeActivityInputSpy homeActivityInputSpy = new HomeActivityInputSpy();
        homePresenter.output = new WeakReference<HomeActivityInput>(homeActivityInputSpy);

        homePresenter.userAccount = getUserAccount();

        //When
        homePresenter.showUser();

        //Then
        Assert.assertTrue(homeActivityInputSpy.callDisplayUsuario);
        Assert.assertTrue(getUserAccount().toString().equalsIgnoreCase(homeActivityInputSpy.userAccountCopy.toString()));
    }

    @Test
    public void showErro_paraExibirToast() {
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeActivityInputSpy homeActivityInputSpy = new HomeActivityInputSpy();
        homePresenter.output = new WeakReference<HomeActivityInput>(homeActivityInputSpy);

        homePresenter.error = getErro().toString();

        //When
        homePresenter.showError();

        //Then
        Assert.assertTrue(homeActivityInputSpy.callShowErro);
        Assert.assertTrue(getErro().toString().equalsIgnoreCase(homeActivityInputSpy.erroCopy));
    }

    @Test
    public void showCurrency_paraRecyclerView() {
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeActivityInputSpy homeActivityInputSpy = new HomeActivityInputSpy();
        homePresenter.output = new WeakReference<HomeActivityInput>(homeActivityInputSpy);

        //When
        homePresenter.apresentarDados(getHomeViewModel());

        //Then
        Assert.assertTrue(homeActivityInputSpy.callDisplayCurrency);
        Assert.assertNotNull(homeActivityInputSpy.statementViewModelCopy);
    }

    private class HomeActivityInputSpy implements HomeActivityInput {

        boolean callDisplayUsuario = false;
        boolean callDisplayCurrency = false;
        boolean callShowErro = false;

        UserAccount userAccountCopy;
        StatementViewModel statementViewModelCopy;
        String erroCopy;


        @Override
        public void displayUsuario(UserAccount userAccount) {
            callDisplayUsuario = true;
            userAccountCopy = userAccount;
        }

        @Override
        public void displayCurrency(StatementViewModel statementViewModel) {
            callDisplayCurrency = true;
            statementViewModelCopy = statementViewModel;
        }

        @Override
        public void showErro(String erro) {
            callShowErro = true;
            erroCopy = erro;
        }
    }
}
