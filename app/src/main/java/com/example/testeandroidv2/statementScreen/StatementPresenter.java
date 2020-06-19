package com.example.testeandroidv2.statementScreen;

import com.example.testeandroidv2.loginScreen.UserModel;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

interface StatementPresenterInput {
    void presentStatementData(StatementResponse response);
    void presentClientData(ClientResponse response);
}

public class StatementPresenter implements StatementPresenterInput {

    public WeakReference<StatementActivityInput> output;

    @Override
    public void presentStatementData(StatementResponse response) {
        if(response.statementList != null && !response.statementList.isEmpty()){
            for(int i=0; i<response.statementList.size(); i++){
                response.statementList.get(i).date = decorateDate(response.statementList.get(i).date);
                response.statementList.get(i).value = decorateMoney(Double.parseDouble(response.statementList.get(i).value));
            }
            output.get().displayStatementData(response.statementList);
        }else{
            output.get().displayStatementError(response.error);
        }
    }

    @Override
    public void presentClientData(ClientResponse response) {
        if(response.userModel != null) {
            output.get().displayClientData(getClienteModel(response.userModel));
        }else{
            output.get().displayClientError(response.error);
        }
    }

    private ClientModel getClienteModel(UserModel userModel){
        ClientModel clientModel = new ClientModel();
        clientModel.name = userModel.getName();
        clientModel.account = decorateAccount(userModel.getAgency(), userModel.getBankAccount());
        clientModel.balance = decorateMoney(userModel.getBalance());
        return clientModel;
    }

    private String decorateAccount(String agency, String bankAccount){
        return String.format(Locale.getDefault(),"%s / %s.%s-%s", bankAccount, agency.substring(0, 2), agency.substring(2, 8), agency.substring(8));
    }

    private String decorateMoney(Double value){
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#,##0.00");
        return ("R$ "+df.format(value));
    }

    private String decorateDate(String date){
        try {
            DateFormat dfParseDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date d = dfParseDate.parse(date);
            DateFormat dfParseString = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return dfParseString.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
