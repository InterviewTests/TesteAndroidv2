package com.dev.appbank.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.dev.appbank.R;

import com.dev.appbank.model.User;
import com.dev.appbank.model.UserAccount;
import com.dev.appbank.util.Common;
import com.dev.appbank.util.RetrofitConfig;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtUser, txtPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Paper.init(this);

        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

        String user = Paper.book().read("user");
        if (user == null || user.equals("")) {
            txtUser.setText("");
        }else{
            txtUser.setText(user);
            txtPassword.requestFocus();
        }
        btnLogin.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String user1 = txtUser.getText().toString();
            String password = txtPassword.getText().toString();
            boolean flag = validatePassword(password);
            if (flag) {
                progressBar.setVisibility(View.GONE);
                Paper.book().write("user", user1);
                Call<User> call = new RetrofitConfig(Common.URL).getServiceRetrofit().signIn(user1, password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        UserAccount userAccount = response.body().getUserAccount();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("userAccount", userAccount);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("TAG", "Nome:" + t.getMessage());
                    }
                });
            } else {
                Log.d("ERRO", "Erro");
            }
            progressBar.setVisibility(View.GONE);
        });

    }

    private boolean validatePassword(String txt) {
        StringBuilder msg = new StringBuilder();
        msg.append("O campo deve conter 8 caracteres.\n");
        msg.append("O campo deve ter uma letra maiúscula.\n");
        msg.append("O campo deve ter um caractere especial.\n");
        msg.append("O campo deve ter um caractere alfanumérico.\n");
        if (!Common.PASSWORD_PATTERN.matcher(txt).matches()) {
            txtPassword.setError(msg);
            return false;
        }
        return true;
    }
}
