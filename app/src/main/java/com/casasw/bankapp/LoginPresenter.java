package com.casasw.bankapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginData(LoginResponse response);
}


public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<LoginActivityInput> output;


    @Override
    public void presentLoginData(LoginResponse response) {
        // Log.e(TAG, "presentLoginData() called with: response = [" + response + "]");
        //Do your decoration or filtering here

        final String USER_ACCOUNT = "userAccount";
        final String USER_ID = "userId";
        final String USER_NAME = "name";
        final String USER_BANK_ACCOUNT = "bankAccount";
        final String USER_AGENCY = "agency";
        final String USER_BALANCE =  "balance";
        int userID = 0, bankAccount = 0, agency = 0;
        String name = "";
        double balance = 0;
        try {
            JSONObject userAccountJSON = new JSONObject(response.getLoginJSON());

            JSONObject userJSON = userAccountJSON.getJSONObject(USER_ACCOUNT);
            userID = userJSON.getInt(USER_ID);
            name = userJSON.getString(USER_NAME);
            bankAccount = userJSON.getInt(USER_BANK_ACCOUNT);
            agency = userJSON.getInt(USER_AGENCY);
            balance = userJSON.getDouble(USER_BALANCE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        LoginViewModel login = new LoginViewModel(userID,name,bankAccount, agency, balance);
        output.get().displayLoginData(login);
    }


}
