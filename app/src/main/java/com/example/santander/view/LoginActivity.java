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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String EXTRA_USER_ACCOUNT = "EXTRA_USER_ACCOUNT";
    private static final String REGEX_PASSWORD_VERIFICATION = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
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

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(REGEX_PASSWORD_VERIFICATION);
        matcher = pattern.matcher(password);

        return matcher.matches();
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

    public void initVariables() {
        etUsername = findViewById(R.id.et_login_user);
        etPassword = findViewById(R.id.et_login_password);
        Button btLogin = findViewById(R.id.bt_login_enter);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidPassword(etPassword.getText().toString()))
                    buildLoginJson();
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.warning_valid_password), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
