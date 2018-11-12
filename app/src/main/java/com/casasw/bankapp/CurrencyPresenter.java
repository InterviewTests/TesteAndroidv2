package com.casasw.bankapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

interface CurrencyPresenterInput {
    public void presentCurrencyData(CurrencyResponse response);
}


public class CurrencyPresenter implements CurrencyPresenterInput {

    public static String TAG = CurrencyPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<CurrencyActivityInput> output;


    @Override
    public void presentCurrencyData(CurrencyResponse response) {
        // Log.e(TAG, "presentCurrencyData() called with: response = [" + response + "]");
        //Do your decoration or filtering here
        final String STATEMENT_LIST = "statementList";
        final String STATEMENT_TITLE = "title";
        final String STATEMENT_DESC = "desc";
        final String STATEMENT_DATE = "date";
        final String STATEMENT_VALUE = "value";
        ArrayList<CurrencyModel> listOfStatements = new ArrayList<>();

        try {
            JSONObject statementJSON = new JSONObject(response.getCurrencyJSON());
            JSONArray statementArray = statementJSON.getJSONArray(STATEMENT_LIST);

            String title = "", desc = "", date = "";
            double balance = 0;
            CurrencyModel currencyModel;
            for (int i =0; i < statementArray.length(); i++) {

                JSONObject statement = statementArray.getJSONObject(i);
                title = statement.getString(STATEMENT_TITLE);
                desc = statement.getString(STATEMENT_DESC);
                date = statement.getString(STATEMENT_DATE);
                balance = statement.getDouble(STATEMENT_VALUE);
                currencyModel = new CurrencyModel(title, desc, date, balance);
                listOfStatements.add(currencyModel);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
        output.get().displayCurrencyData(listOfStatements);
    }


}
