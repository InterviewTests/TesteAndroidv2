package com.brunorfreitas.teste_ibm_santander.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brunorfreitas.teste_ibm_santander.R;
import com.brunorfreitas.teste_ibm_santander.helper.Preferencias;
import com.brunorfreitas.teste_ibm_santander.helper.ValidaCPF;
import com.brunorfreitas.teste_ibm_santander.model.UserAccount;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private Context context;

    private EditText et_login;
    private EditText et_password;
    private CardView btn_login;
    private ProgressBar progressBar;

    private LinearLayout ll_user_save;
    private TextView tv_userSave;

    private String msg;

    private Preferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializaVariaves();
        inicializaAcoes();
    }

    private void inicializaVariaves() {

        context = getBaseContext();
        et_login = findViewById(R.id.activity_login_et_login);
        et_password = findViewById(R.id.activity_login_et_password);
        btn_login = findViewById(R.id.activity_login_btn_login);
        progressBar = findViewById(R.id.activity_login_pb);

        ll_user_save = findViewById(R.id.activity_login_ll_userSalvo);
        tv_userSave = findViewById(R.id.user_salvo_tv_username);

        preferencias = new Preferencias(context);

        verificaUsuarioSalvo();

    }

    private void verificaUsuarioSalvo() {

        String userSave = preferencias.getNomeUsuarioSalvo();

        if(userSave != null){
            ll_user_save.setVisibility(View.VISIBLE);
            tv_userSave.setText(preferencias.getNomeUsuarioSalvo());
        }
    }

    private void inicializaAcoes() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validUser()) {
                    login();
                } else {
                    showError();
                }
            }
        });

        ll_user_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_login.setText(preferencias.getNomeUsuarioSalvo());

            }
        });
    }

    private void login() {

        final String user = et_login.getText().toString().trim();
        final String password = et_password.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

//        new PostAndReadJson(user, password).execute();

        final OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "user=" + user + "&password=" + password);
        Request request = new Request.Builder()
                .url("https://bank-app-test.herokuapp.com/api/login")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                progressBar.setVisibility(View.GONE);
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, R.string.var_erro_conectar_tente_mais_tarde, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(myResponse);
                                JSONObject object = jsonObject.getJSONObject("userAccount");

                                String id = object.getString("userId");
                                String name = object.getString("name");
                                String bankAccount = object.getString("bankAccount");
                                String agency = object.getString("agency");
                                Double balance = Double.parseDouble(object.getString("balance"));

                                UserAccount userAccount1 = new UserAccount(Long.parseLong(id), name, bankAccount, agency, balance);

                                progressBar.setVisibility(View.GONE);

                                preferencias.salvarUsuarioPreferencias(user);

                                callNextActivity(userAccount1);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, R.string.var_erro_conectar, Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void showError() {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean validUser() {

        String user = et_login.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (user.isEmpty()) {
            msg = getString(R.string.var_user_vazio);
            return false;
        }

        if (password.isEmpty()) {
            msg = getString(R.string.var_senha_vazia);
            return false;
        }

        if (isEmail(user) || ValidaCPF.isCPF(user)) {
            if(isValidPassword(password)){
                return true;
            }else{
                msg = getString(R.string.var_erro_senha);
                return false;
            }
        }else{
            msg = getString(R.string.var_msg_erro_user);
            return false;
        }

//        if(!isValidPassword(password)){
//            msg = "A senha deve ter pelo menos uma letra maiuscula, um caracter especial e um caracter alfanumérico";
//            return false;
//        }

//        return true;

//        if (isEmail(user) || ValidaCPF.isCPF(user)) {
//
//            verificaSenha(password);
//
//        } else {
////            msg = "teste";
////            Toast.makeText(context, "teste", Toast.LENGTH_SHORT).show();
//            msg = getString(R.string.var_msg_erro_user);
//            return false;
//        }

//        return false;
    }


    private boolean isEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    private boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.find();
    }

    private void callNextActivity(UserAccount userAccount) {
        Intent mIntent = new Intent(context, MainActivity.class);
        mIntent.putExtra("id", userAccount.getUserid());
        mIntent.putExtra("name", userAccount.getName());
        mIntent.putExtra("bankAccount", userAccount.getBankaccount());
        mIntent.putExtra("agency", userAccount.getAgency());
        mIntent.putExtra("balance", userAccount.getBalance());

        startActivity(mIntent);


    }
}
