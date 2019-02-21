package com.avanade.testesantander2.loginScreen;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avanade.testesantander2.R;
import com.avanade.testesantander2.UserAccount;

import java.util.List;

interface LoginActivityInput {

    // Exibe TOAST com os Erros da Lista
    void showErros(List<String> erros);

    // sucesso ao logar - solicita abrir HomeActivity
    void openHomeScreen(UserAccount userAccount);

    void setUsuario(String user, String password);  // recebe os dados do último usuário logado
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    public static final String TAG = LoginActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PERMISSION = 101;

    LoginInteractorInput output;
    LoginRouterInput router;
    LoginRequest loginRequest;
    List<String> listaDeErros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginConfigurator.INSTANCE.configure(this);
    }

    void checaUsuarioSalvo() {
        loading(true);
        output.checkUsuarioSalvo(getApplicationContext());
    }

    public void loading(boolean isLoading) {
        View layoutLoading = findViewById(R.id.layout_loading);

        if (isLoading)
            layoutLoading.setVisibility(View.VISIBLE);
        else
            layoutLoading.setVisibility(View.INVISIBLE);
    }

    public void clickLogin(View view) {
        loading(true);

        EditText edtUser = findViewById(R.id.edt_user);
        String usuario = edtUser.getText().toString().toLowerCase();

        EditText edtPassword = findViewById(R.id.edt_password);
        String senha = edtPassword.getText().toString();

        loginRequest = new LoginRequest();
        loginRequest.user = usuario;
        loginRequest.password = senha;

        output.postLogin(getApplicationContext(), loginRequest);
    }

    @Override
    public void showErros(List<String> erros) {

        listaDeErros = erros;

        if (!erros.isEmpty()) {
            // Apaga o loading para exibir algum erro
            loading(false);

            for (String erro : erros)
                Toast.makeText(this, erro, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void openHomeScreen(UserAccount userAccount) {
        loading(true);
        router.openHomeScreen(userAccount);
    }

    @Override
    public void setUsuario(String user, String password) {

        loginRequest = new LoginRequest();
        loginRequest.user = user;
        loginRequest.password = password;

        EditText usuario = findViewById(R.id.edt_user);
        usuario.setText(user);

        EditText senha = findViewById(R.id.edt_password);
        senha.setText(password);

        loading(false);
    }


    @Override
    protected void onResume() {
        super.onResume();
        checaUsuarioSalvo();
        loading(false);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage("Finalizar o App Bank?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("BACK PRESSED", "Alert: Finalizar Aplicativo? -> Clicou em SIM");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                            finishAndRemoveTask();
                        else
                            finish();
                    }
                })
                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("BACK PRESSED", "Alert: Finalizar Aplicativo? -> Clicou em NÃO");
                        dialog.dismiss();
                    }
                })
        ;
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}