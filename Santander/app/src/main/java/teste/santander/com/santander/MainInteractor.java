package teste.santander.com.santander;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface MainInteractorInput {
    void fetchMainMetaData(MainRequest request);
}

public class MainInteractor implements MainInteractorInput {
    public MainPresenterInput output;
    public WeakReference<MainActivity> activity;
    ProgressDialog nDialog;

    public ClientModel clientModel = new ClientModel();


    public boolean callLogin(EditText usernameEt, EditText passwordEt) {

        if(usernameEt.length()==0)
        {
            usernameEt.requestFocus();
            usernameEt.setError("CAMPO NÃO PODE ESTAR VAZIO");
            return false;
        }
        else if (passwordEt.length()==0) {
            passwordEt.requestFocus();
            passwordEt.setError("DIGITE A SENHA");
            return false;
        } else if (!isValidPassword(passwordEt.getText().toString())) {
            passwordEt.requestFocus();
            passwordEt.setError("SENHA INVÁLIDA");
            return false;
        }else {
            return true;
        }
    }

    public boolean login (EditText usernameEt, EditText passwordEt){

        boolean validation;
        validation = callLogin(usernameEt, passwordEt);
        if(validation) {
            nDialog = new ProgressDialog(activity.get());
            nDialog.setMessage("Carregando dados..");
            nDialog.setTitle("Por favor aguarde");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(false);
            nDialog.show();
            callRequest(usernameEt.getText().toString(), passwordEt.getText().toString());
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPassword(String password) {

        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public void callRequest(final String user, final String password){

        RequestQueue queue = Volley.newRequestQueue(activity.get());

        StringRequest sr = new StringRequest(Request.Method.POST,"https://bank-app-test.herokuapp.com/api/login", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                JSONObject obj = null;
                JSONObject obj2 = null;

                try {
                    obj = new JSONObject(response);
                    obj2 = new JSONObject(String.valueOf(obj.get("userAccount")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String a = obj.get("userAccount").toString();

                    if(a.length() <= 2) {
                    } else {
                        clientModel.setName(obj2.getString("name"));
                        clientModel.setBankAccount(obj2.getString("bankAccount"));
                        clientModel.setUserId(Integer.parseInt(obj2.getString("userId")));
                        clientModel.setAgency(obj2.getString("agency"));
                        clientModel.setBalance(Double.parseDouble(obj2.getString("balance")));

                        Currency c = new Currency();
                        c.setData(clientModel);

                        SecurePreferences preferences = new SecurePreferences(activity.get(), "user-secure-data", "SantBank23@4", true);
                        preferences.put("lastUser", user);
                        Log.d("safas", "Cheguei");
                        Intent intent = new Intent (activity.get(), Currency.class);
                        activity.get().startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user",user);
                params.put("password",password);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    @Override
    public void fetchMainMetaData(MainRequest request){

        login(request.user, request.pass);
        //callLogin();

        //isValidPassword();
        //callRequest();

    }

}
