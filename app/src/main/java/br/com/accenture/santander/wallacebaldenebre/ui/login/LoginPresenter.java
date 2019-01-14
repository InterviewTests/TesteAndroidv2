package br.com.accenture.santander.wallacebaldenebre.ui.login;

import android.os.AsyncTask;
import android.util.Log;
import br.com.accenture.santander.wallacebaldenebre.model.Error;
import br.com.accenture.santander.wallacebaldenebre.model.LoginModel;
import br.com.accenture.santander.wallacebaldenebre.model.User;
import br.com.accenture.santander.wallacebaldenebre.model.UserAccount;
import br.com.accenture.santander.wallacebaldenebre.ui.base.BasePresenter;
import br.com.accenture.santander.wallacebaldenebre.utils.Helper;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class LoginPresenter<V extends LoginContract.View> extends BasePresenter<V> implements LoginContract.Presenter<V> {
    @Override
    public void makeLogin(LoginActivity login, String user, String password) {
        new MakeLogin(login, user, password).execute();
    }

    public class MakeLogin extends AsyncTask<Void, Void, Void> {
        private LoginCallback<HashMap<String, User>> callback;
        private LoginActivity login;
        private String user;
        private String password;

        public MakeLogin(LoginActivity login, String user, String password) {
            this.login = login;
            this.user = user;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            login.showProgress();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HashMap<String, String> hashLogin = new HashMap<>();
            LoginModel l = new LoginModel(user, password);

            hashLogin.put("user", l.getuser());
            hashLogin.put("password", l.getPassword());

            String jsonString = Helper.reqPOST("https://bank-app-test.herokuapp.com/api/login", hashLogin);

            if (jsonString != null) {
                try {
                    JSONObject jo = new JSONObject(jsonString);
                    JSONObject main = jo.getJSONObject(jsonString);

                    JSONObject bill = main.getJSONObject("userAccount");
                    int id = bill.getInt("userId");
                    String name = bill.getString("name");
                    String bank = bill.getString("bankAccount");
                    String agency = bill.getString("agency");
                    Double balance = bill.getDouble("balance");

                    JSONObject error = jo.getJSONObject("error");

                    HashMap<String, User> dataTemp = new HashMap<>();
                    dataTemp.put("user", new User(new UserAccount(id, name, bank, balance, agency), new Error()));

                    Log.e("LOG", "LoginPresenter - doInBackground: User -> \nId: " + id + "\nNome: " + name + "\nBank: " + bank);

                    callback.onSuccess(dataTemp);
                } catch (Exception e) {
                    login.hideProgress();
                    e.printStackTrace();
                    Log.e("LOG", "LoginPresenter - doInBackground: Error -> " + e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            login.hideProgress();
        }
    }
}
