package br.com.amilton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.com.amilton.databinding.ActivityLoginBinding;
import br.com.amilton.model.Login;
import br.com.amilton.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding activityLoginBinding;
    private static final String PREFERENCES_LOGIN = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        activityLoginBinding.setViewModel(new ViewModelProvider(this).get(LoginViewModel.class));

        activityLoginBinding.getViewModel().login.set(getPreferences(MODE_PRIVATE).getString(PREFERENCES_LOGIN,""));

        activityLoginBinding.getViewModel().loading.observe(this, aBoolean -> {
            if (aBoolean) {
                activityLoginBinding.progressbar.setVisibility(View.VISIBLE);
                activityLoginBinding.btnLogin.setVisibility(View.GONE);
            } else {
                activityLoginBinding.progressbar.setVisibility(View.GONE);
                activityLoginBinding.btnLogin.setVisibility(View.VISIBLE);
            }
        });

        activityLoginBinding.getViewModel().getErrorMessage().observe(this, s -> Toast.makeText(this, s, Toast.LENGTH_SHORT).show());

        activityLoginBinding.getViewModel().getUser().observe(this, login -> {
            if (Login.EMPTY_LOGIN.equals(login)) {
                Toast.makeText(this, "Erro desconhecido",Toast.LENGTH_LONG).show();
            } else {
                getPreferences(MODE_PRIVATE).edit().putString(PREFERENCES_LOGIN, activityLoginBinding.getViewModel().login.get()).apply();
                startActivity(StatementActivity.newInstance(this, login.getUserAccount()));
                finishAffinity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
