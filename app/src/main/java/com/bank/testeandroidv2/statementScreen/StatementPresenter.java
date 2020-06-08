package com.bank.testeandroidv2.statementScreen;


import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

interface StatementPresenterInput {
    void presentStatementHeaderData(StatementHeaderResponse response);
//    void processRequestFetchStatement(StatementModel response);
//    void processRequestFetchStatement(StatementList response);
    void processRequestFetchStatement(StatementResponse response);
    void processErrorRequest(String error);
}


public class StatementPresenter implements StatementPresenterInput {

    public static String TAG = StatementPresenter.class.getSimpleName();

    public WeakReference<StatementActivityInput> output;


    @Override
    public void presentStatementHeaderData(StatementHeaderResponse response) {
        StatementHeaderViewModel statementHeaderViewModel = new StatementHeaderViewModel();
        statementHeaderViewModel.name = response.name;
        statementHeaderViewModel.bankAccount = formatBankAgency(response.bankAccount,response.agency);
//        statementHeaderViewModel.balance = "R$"+response.balance;
        statementHeaderViewModel.balance = formatCurrency("",response.balance);
        output.get().displayStatementDataHeader(statementHeaderViewModel);
    }

    @Override
    public void processRequestFetchStatement(StatementResponse response) {
//    public void processRequestFetchStatement(StatementList response) {
//        public void processRequestFetchStatement(StatementModel response) {
        StatementViewModel statementViewModel = new StatementViewModel();
        statementViewModel.list = new ArrayList<>();
        if (response.list != null) {
            for(StatementModel sm : response.list) {
                StatementViewModel svm = new StatementViewModel();
                svm.date = sm.getDate();
                svm.desc = sm.getDesc();
                svm.title = sm.getTitle();
                if (sm.getValue().contains("-")) {
                    svm.positive = false;
                    svm.value = formatCurrency(sm.getValue().replace("-",""),null);
                }else {
                    svm.positive = true;
                    svm.value = formatCurrency(sm.getValue(),null);
                }
                statementViewModel.list.add(svm);
            }
            output.get().displayStatementData(statementViewModel);
        }
        else {
            processErrorRequest(null);
        }
    }

    @Override
    public void processErrorRequest(String error) {
        if(null == error) {
            String msg = "Null response from server";
            output.get().callApiError(msg);
        }
        else {
            output.get().callApiError(error);
        }
    }
    private String formatBankAgency(String agency, String account) {
        String bankAccount = agency + " / " + addChar(addChar(account,".",2),"-",9);
        return bankAccount;
    }

    private String addChar(String str, String ch, int position) {
        return str.substring(0, position) + ch + str.substring(position);
    }

    private String formatCurrency(String sCurrency, Double dCurrency) {
        double d = 0.00;
        if(sCurrency.isEmpty() && dCurrency != null)
            d = dCurrency;
        else if(!sCurrency.isEmpty() && dCurrency == null)
            d = Double.valueOf(sCurrency);
        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(d);
        return valorString;
    }

}
