package com.home.project.testeandroidv2.loginScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.home.project.testeandroidv2.R;
import com.home.project.testeandroidv2.databinding.ActivityLoginBinding;
import com.home.project.testeandroidv2.model.LoginResponse;
import com.home.project.testeandroidv2.model.LoginRequest;
import com.home.project.testeandroidv2.model.UserAccount;

interface LoginActivityInput {
    void displayUserLoginData(LoginResponseActivity loginResponseActivity);
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    private Context context = this;
    private String[] specialCharacters = {"+", "*", "=", "/", "_", "-", "!", "@", "#", "%", "$", "&", "(", ")", "^", "?", "'",
            "{", "}", "[", "]", ";", "`", "´", ".", "~", "|", "§", "°", "º", "ª"};
    private LoginViewModel loginViewModel;
    public LoginRouter loginRouter;
    private ActivityLoginBinding binding;
    public LoginInteractor output;
    public UserAccount userAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        LoginConfigurator.INSTANCE.configure(this);

        getUserLoginLogin();

    }


    public void observerLogin() {
        loginViewModel.loginResponse().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(@Nullable LoginResponse loginResponse) {
                binding.progressBarLogin.setVisibility(View.GONE);
                if (loginResponse != null) {
                    userAccount = loginResponse.getUserAccount();
                    loginRouter.startNextAcitivy();
                } else {
                    binding.btnLogin.setVisibility(View.VISIBLE);
                    Toast.makeText(context, "Ocorreu um erro ao executar a operação, verifique sua conexão" +
                            " com a internet.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public boolean formatEdtUser(String user) {
        boolean isDigit = false;
        boolean containsNineNumbers = false;
        int cont = 0;
        for (int x = 0; x < user.length(); x++) {
            if (Character.isDigit(user.charAt(x))) {
                cont =  cont + 1;
            }
        }

        if(cont == user.length()){
            isDigit = true;
        }

        if (user.length() == 11) {
            containsNineNumbers = true;
        }

        if (user.contains("@") && user.contains("@") &&
                user.contains(".com") || isDigit && containsNineNumbers) {
            return true;
        } else {
            return false;
        }

    }

    public boolean formatEdtPassWord(String senha) {
        boolean validUpperCase = false;
        boolean validaSpecialCharacters = false;
        boolean validaAlphanumeric = false;

        for (int x = 0; x < senha.length(); x++) {
            if (Character.isUpperCase(senha.charAt(x))) {
                validUpperCase = true;
                break;
            }

        }

        for (int x = 0; x < specialCharacters.length; x++) {
            if (senha.contains(specialCharacters[x])) {
                validaSpecialCharacters = true;
                break;

            }

        }

        for (int x = 0; x < senha.length(); x++) {
            if (Character.isAlphabetic(senha.charAt(x)) ||
                    Character.isDigit(senha.charAt(x))) {
                validaAlphanumeric = true;
                break;
            }

        }

        if (validUpperCase && validaSpecialCharacters && validaAlphanumeric) {
            return true;
        } else {
            return false;
        }

    }

    public void onClickLogin(View view) {
        binding.btnLogin.setVisibility(View.GONE);
        binding.progressBarLogin.setVisibility(View.VISIBLE);
        if (!formatEdtUser(binding.edtUser.getText().toString())) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Aviso");
            alertDialog.setMessage("Por favor, logar com email ou com somente os números do cpf.");
            alertDialog.setPositiveButton("OK", null);
            alertDialog.show();
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.progressBarLogin.setVisibility(View.GONE);
        } else if (!formatEdtPassWord(binding.edtPassword.getText().toString())) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Aviso");
            alertDialog.setMessage("A senha deve conter pelo menos uma letra maiúscula," +
                    " um caracter especial e um caracter alfanumérico.");
            alertDialog.setPositiveButton("OK", null);
            alertDialog.show();
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.progressBarLogin.setVisibility(View.GONE);
        } else {
            LoginRequest loginRequest = new LoginRequest(binding.edtUser.getText().toString(), binding.edtPassword.getText().toString());
            loginViewModel.login(loginRequest);
            observerLogin();

        }
    }

    public void onClickRemoveAccount(View view) {
        loginViewModel.removeUserAccount(context);
        binding.setUserAccount(new UserAccount());
        binding.edtUser.setVisibility(View.VISIBLE);
        binding.edtUser.setText("");
        binding.tvRemoveAccount.setVisibility(View.GONE);
    }

    @Override
    public void displayUserLoginData(LoginResponseActivity loginResponseActivity) {
        //exibe na activity os dados do último usuário apresentados pelo LoginPresenter
       UserAccount userAccount = loginResponseActivity.userAccount;
        if (!userAccount.getName().isEmpty()) {
            binding.setUserAccount(userAccount);
            binding.edtUser.setText(userAccount.getLogin());
            binding.edtUser.setVisibility(View.GONE);
            binding.tvRemoveAccount.setVisibility(View.VISIBLE);
        } else {
            binding.setUserAccount(new UserAccount());

        }
    }

    public void getUserLoginLogin(){
        output.getUserAccount(context);
    }
}
