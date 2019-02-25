package com.santander.app.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.santander.app.R;
import com.santander.app.api.Api;
import com.santander.app.bean.UserAccount;
import com.santander.app.util.Validacao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private Context context;

    @ViewById(R.id.etUser)
    EditText etUser;

    @ViewById(R.id.etPassword)
    EditText etPassword;

    @ViewById(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    @AfterViews
    protected void afer(){
        context = this;

        if(getSharedPreferences("BankApp", MODE_PRIVATE).contains("user")){
            etUser.setText(getSharedPreferences("BankApp", MODE_PRIVATE).getString("user", ""));
        }
    }

    @Click(R.id.btLogin)
    void btLoginClick(){
        if(Validacao.isUserOK(etUser.getText().toString())){
            if(Validacao.isPasswordOK(etPassword.getText().toString())) {

                progress.setVisibility(View.VISIBLE);

                RequestParams params = new RequestParams();
                params.put("user", etUser.getText().toString());
                params.put("password", etPassword.getText().toString());

                Api.post("login", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        progress.setVisibility(View.GONE);

                        JSONObject jsonErro = response.optJSONObject("error");
                        if (jsonErro.length() == 0) {
                            JSONObject jsonDados = response.optJSONObject("userAccount");
                            UserAccount conta = new UserAccount();
                            conta.setAgency(jsonDados.optString("agency"));
                            conta.setBalance(jsonDados.optDouble("balance"));
                            conta.setBankAccount(jsonDados.optString("bankAccount"));
                            conta.setName(jsonDados.optString("name"));
                            conta.setUserId(jsonDados.optInt("userId"));

                            SharedPreferences preferences = getSharedPreferences("BankApp", MODE_PRIVATE);
                            preferences.edit().putString("user", etUser.getText().toString()).commit();

                            Intent intent = new Intent(context, MainActivity_.class);
                            intent.putExtra("userAccount", conta);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(context, R.string.erro_login, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        progress.setVisibility(View.GONE);
                        Toast.makeText(context, R.string.erro_login, Toast.LENGTH_LONG).show();
                    }
                });
            }
            else{
                Toast.makeText(context, R.string.erro_login, Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context, R.string.erro_login, Toast.LENGTH_LONG).show();
        }
    }
}
