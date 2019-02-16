package com.santander.ian.santanderauth.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.santander.ian.santanderauth.Model.User;
import com.santander.ian.santanderauth.R;
import com.santander.ian.santanderauth.Utils.Auxiliar;
import com.santander.ian.santanderauth.Utils.Constantes;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Auth extends AppCompatActivity {

    private Context context;
    private Button auth_btn_login;
    private EditText auth_et_user;
    private EditText auth_et_password;

    private RequestQueue queue;

    private String user;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();

        queue = Volley.newRequestQueue(this);

        auth_btn_login = findViewById(R.id.auth_btn_login);
        auth_et_user = findViewById(R.id.auth_et_user);
        auth_et_password = findViewById(R.id.auth_et_password);

        get_last_user ();

    }

    private void initActions() {

        auth_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                read_ui ();

                if ( validation() ) {
                    post_and_read_json();
                } else {
                    Toast.makeText(context, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void go_to_currency (User user) {
        Intent mIntent = new Intent(context,Currency.class);
        mIntent.putExtra(Constantes.AUTHUSER, user);

        startActivity(mIntent);
        finish();
    }

    private boolean validation() {

        if ( !Auxiliar.strong_password(password) || (user.trim().equals(""))) {
            return false;
        }

        return true;
    }

    private void read_ui() {
        user = auth_et_user.getText().toString().trim();
        password = auth_et_password.getText().toString();

    }

    private void save_user() {
        SharedPreferences.Editor editor = getSharedPreferences(Constantes.LASTAUTHUSER, MODE_PRIVATE).edit();
        editor.putString(Constantes.USER, user);
        editor.apply();
    }

    private void get_last_user () {
        SharedPreferences prefs = getSharedPreferences(Constantes.LASTAUTHUSER, MODE_PRIVATE);
        String restoredText = prefs.getString(Constantes.USER, null);
        if (restoredText != null) {
            String lastuser = prefs.getString(Constantes.USER, "");//"No name defined" is the default value.
            auth_et_user.setText(lastuser);
            auth_et_password.requestFocus();
        }
    }

    private void clean_fields() {
        auth_et_password.setText("");
        auth_et_user.setText("");

        auth_et_user.requestFocus();
    }

    private void post_and_read_json() {

        String url = Constantes.API_LOGIN;

        auth_btn_login.setEnabled(false);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response

                        try {
                            //convert to JSON
                            JSONObject responseJsonObject = new JSONObject(response);

                            JSONObject error = responseJsonObject.getJSONObject("error");
                            if (error.length() > 0) {

                                //long code = error.getLong("code");
                                String message = error.getString("message");

                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                auth_btn_login.setEnabled(true);
                                clean_fields();

                            }
                            else {
                                JSONObject userAccount = responseJsonObject.getJSONObject("userAccount");
                                if (userAccount.length() > 0) {
                                    User auth_user = new User(
                                            userAccount.getLong("userId"),
                                            userAccount.getString("name"),
                                            Long.valueOf(userAccount.getString("bankAccount")),
                                            Long.valueOf(userAccount.getString("agency")),
                                            new BigDecimal(userAccount.getString("balance"))
                                    );

                                    save_user();

                                    go_to_currency(auth_user);
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());

                        auth_btn_login.setEnabled(true);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put(Constantes.USER, user);
                params.put(Constantes.PASSWORD, password);

                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        queue.add(postRequest);
        


    }

}
