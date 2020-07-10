package com.accenture.android.app.testeandroid.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.accenture.android.app.testeandroid.databinding.ActivityLoginBinding;
import com.accenture.android.app.testeandroid.helpers.FieldHelper;
import com.accenture.android.app.testeandroid.presentation.main.MainActivity;
import com.google.android.material.snackbar.Snackbar;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private final static String TAG = "CustomLog - " + LoginActivity.class.getSimpleName();

    private ActivityLoginBinding binding;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityLoginBinding.inflate(getLayoutInflater());

        this.presenter = new LoginPresenter(this, this.getLifecycle(), this);

        setContentView(this.binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.initEvents();
    }

    private void initEvents() {
        this.binding.btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    String user = binding.edtUser.getText().toString();
                    String password = binding.edtPassword.getText().toString();

                    presenter.efetuarLogin(user, password);
                } else {
                    setFeedback("Revise os campos!");
                }
            }
        });
    }

    @Override
    public void setLoading() {
        this.binding.pgbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void unsetLoading() {
        this.binding.pgbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setContent() {
        this.binding.btnLogar.setEnabled(true);
    }

    @Override
    public void unsetContent() {
        this.binding.btnLogar.setEnabled(false);
    }

    @Override
    public void setFeedback(String message) {
        Snackbar.make(this.binding.getRoot(), message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setError(String message) {
        this.binding.txtFeedbackError.setVisibility(View.VISIBLE);
        this.binding.txtFeedbackError.setText(message);
    }

    @Override
    public void unsetError() {
        this.binding.txtFeedbackError.setVisibility(View.GONE);
        this.binding.txtFeedbackError.setText("");
    }

    @Override
    public void setLoginRecente(String name) {
        this.binding.lltRecentLoginContainer.setVisibility(View.VISIBLE);
        this.binding.txtLoginRecente.setText(name);
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);

        this.finish();
    }

    private boolean validateFields() {
        boolean filled = true;

        // Valida campo "User"
        this.binding.tilUser.setErrorEnabled(false);
        if (this.binding.edtUser.getText().toString().isEmpty()) {
            this.binding.tilUser.setError("Campo obrigatório!");
            filled = false;
        } else if (this.binding.edtUser.getText().toString().contains("@")) {
            if (!FieldHelper.isEmail(this.binding.edtUser.getText().toString())) {
                this.binding.tilUser.setError("CPF ou e-mail inválido 1!");
                filled = false;
            }
        } else if (!FieldHelper.isCPF(this.binding.edtUser.getText().toString())) {
            this.binding.tilUser.setError("CPF ou e-mail inválido 2!");
            filled = false;
        }

        this.unsetError();
        // Valida campo "Password"
        this.binding.tilPassword.setErrorEnabled(false);
        if (this.binding.edtPassword.getText().toString().isEmpty()) {
            this.binding.tilPassword.setError("Campo obrigatório!");
            filled = false;
        } else if (this.binding.edtPassword.getText().length() < 3) {
            this.binding.tilPassword.setError("Campo inválido!");
            filled = false;
        } else if (!FieldHelper.isPassword(this.binding.edtPassword.getText().toString())) {
            this.binding.tilPassword.setError("Campo inválido!");
            this.setError("A senha deve conter pelo menos uma letra maiúscula, um caracter especial e um caracter alfanumérico.");

            filled = false;
        }

        return filled;
    }
}
