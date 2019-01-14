package br.com.accenture.santander.wallacebaldenebre.ui.account;

import android.os.AsyncTask;
import android.util.Log;
import br.com.accenture.santander.wallacebaldenebre.model.Account;
import br.com.accenture.santander.wallacebaldenebre.model.StatementList;
import br.com.accenture.santander.wallacebaldenebre.ui.base.BasePresenter;
import br.com.accenture.santander.wallacebaldenebre.utils.Helper;
import br.com.accenture.santander.wallacebaldenebre.model.Error;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountPresenter<V extends AccountContract.View> extends BasePresenter<V> implements AccountContract.Presenter<V> {
    @Override
    public void showDataFromServer(AccountActivity account, AccountCallback<HashMap<String, Account>> callback) {
        new GetBills(account, callback).execute();
    }

    public class GetBills extends AsyncTask<Void, Void, Void> {
        private AccountCallback<HashMap<String, Account>> callback;
        private AccountActivity account;

        GetBills(AccountActivity account, AccountCallback<HashMap<String, Account>> callback) {
            this.account = account;
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            account.showProgress();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String jsonString = Helper.reqGET("https://bank-app-test.herokuapp.com/api/statements/1");

            if (jsonString != null) {
                try {
                    JSONObject jo = new JSONObject(jsonString);

                    ArrayList<StatementList> statements = new ArrayList<>();
                    JSONArray billArray = jo.getJSONArray("statementList");

                    for (int j = 0; j < billArray.length(); j++) {
                        JSONObject singleBill = billArray.getJSONObject(j);
                        String title = singleBill.getString("title");
                        String type = singleBill.getString("desc");
                        String date = singleBill.getString("date");
                        long value = singleBill.getLong("value");

                        Log.d("LOG", "AccountPresenter\nTitle: "+title);
                        statements.add(new StatementList(title, date, type, value));
                    }

                    HashMap<String, Account> tempData = new HashMap<>();
                    tempData.put("statements", new Account(statements, new Error()));

                    callback.onSuccess(tempData);
                } catch (Exception e) {
                    account.hideProgress();
                    e.printStackTrace();
                    Log.e("LOG", "AccountPresenter - doInBackground: Error -> " + e.getMessage()+"\nCausa: "+ e.getLocalizedMessage());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            account.hideProgress();
        }
    }
}
