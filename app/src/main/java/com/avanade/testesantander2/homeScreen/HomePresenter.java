package com.avanade.testesantander2.homeScreen;

import com.avanade.testesantander2.Statement;
import com.avanade.testesantander2.UserAccount;
import com.avanade.testesantander2.util.DateUtil;
import com.avanade.testesantander2.util.MonetarioUtil;

import org.joda.time.DateTime;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

interface HomePresenterInput {
    void apresentarUsuario(UserAccount userAccount);

    void apresentarDados(HomeViewModel homeViewModel);

    void apresentarErro(String erro);
}

public class HomePresenter implements HomePresenterInput {

    private static String TAG = HomePresenter.class.getSimpleName();
    public WeakReference<HomeActivityInput> output;

    UserAccount userAccount;
    StatementViewModel statementViewModel;
    String error;

    @Override
    public void apresentarUsuario(UserAccount userAccount) {
        this.userAccount = userAccount;
        showUser();
    }

    void showUser(){
        output.get().displayUsuario(userAccount);
    }

    @Override
    public void apresentarDados(HomeViewModel homeViewModel) {
        // Encapsulando a lista de apresentação dentro do ViewModel
        statementViewModel = new StatementViewModel();
        statementViewModel.listaStatement = formataListaDeStatements(homeViewModel);
        showCurrency();
    }

    ArrayList<StatementModel> formataListaDeStatements(HomeViewModel homeViewModel){
        ArrayList<StatementModel> listaDeStatements = new ArrayList<>();

        for (Statement s : homeViewModel.currencyAccount) {

            DateTime dt = DateUtil.stringUSToDateTime(s.getDate());
            String data = DateUtil.dateTimeToDate(dt);
            String valor = MonetarioUtil.formataMoedaPtBr(s.getValue());
            //String valor = "R$" + MonetarioUtil.formataNumeroPtBr(s.getValue());
            //String valor = MonetarioUtil.formataMoedaPtBrDecimal(s.getValue());

            listaDeStatements.add(new StatementModel(s.getTitle(), s.getDesc(), data, valor));
        }
        return listaDeStatements;
    }

    void showCurrency(){
        output.get().displayCurrency(statementViewModel);
    }


    @Override
    public void apresentarErro(String erro) {
        error = erro;
        showError();
    }

    void showError(){
        output.get().showErro(error);
    }
}
