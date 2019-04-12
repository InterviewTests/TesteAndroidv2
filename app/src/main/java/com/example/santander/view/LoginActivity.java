package com.example.santander.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.santander.R;
import com.example.santander.model.loginVO;
import com.example.santander.model.userAccountVO;
import com.example.santander.viewmodel.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static String EXTRA_USER_ACCOUNT = "EXTRA_USER_ACCOUNT";
    private loginVO loginVO;
    private userAccountVO userAccountVO;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initVariables();
    }

    public void initVariables() {
        etUsername = findViewById(R.id.et_login_user);
        etPassword = findViewById(R.id.et_login_password);
        Button btLogin = findViewById(R.id.bt_login_enter);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildLoginJson();
            }
        });
    }

    private void buildLoginJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<loginVO> call = api.getLogin(etUsername.getText().toString(), etPassword.getText().toString());
        call.enqueue(new Callback<loginVO>() {
            @Override
            public void onResponse(@NonNull Call<loginVO> call, @NonNull Response<loginVO> response) {
                loginVO = response.body();
                if (loginVO != null) {
                    userAccountVO = loginVO.getUserAccount();
                    Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                    intent.putExtra(EXTRA_USER_ACCOUNT, userAccountVO);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(@NonNull Call<loginVO> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
