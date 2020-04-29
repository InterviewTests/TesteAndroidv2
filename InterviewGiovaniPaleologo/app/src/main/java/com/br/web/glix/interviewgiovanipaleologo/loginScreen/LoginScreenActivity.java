package com.br.web.glix.interviewgiovanipaleologo.loginScreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.br.web.glix.interviewgiovanipaleologo.ActivityBaseCommon;
import com.br.web.glix.interviewgiovanipaleologo.R;
import com.br.web.glix.interviewgiovanipaleologo.homeScreen.HomeScreenActivity;
import com.br.web.glix.interviewgiovanipaleologo.models.User;
import com.br.web.glix.interviewgiovanipaleologo.models.UserAccount;
import com.google.gson.Gson;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static com.br.web.glix.interviewgiovanipaleologo.utils.Utils.VerificaConexao;
import static com.br.web.glix.interviewgiovanipaleologo.utils.Utils.getSoftButtonsBarSizePort;


interface LoginScreenActivityInput {
    void savePreferences(String userLogin);
    void setUserAccountData(UserAccount userAccount);
    void nextScreen();

    boolean verificarConexao();
    void connectionRefused();
    void emptyUsername();
    void emptyPassword();
    void invalidUsername();
    void invalidPassword();
    void invalidLogin();
    void clearPassword();
}


public class LoginScreenActivity extends ActivityBaseCommon implements LoginScreenActivityInput {
    public LoginScreenInteractor loginScreenInteractor;

    public static UserAccount userAccount;

    private SharedPreferences sharedPreferences;

    User user;
    EditText etUserName;
    EditText etPassword;
    Button btLogin;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loginscreen);

        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        ConstraintLayout constraintLayout = findViewById(R.id.clLogin);
        constraintLayout.setPadding(0,0,0, getSoftButtonsBarSizePort(this));

        sharedPreferences = this.getSharedPreferences(getString(R.string.prefKey), Context.MODE_PRIVATE);
        displayScreenData();

        LoginScreenConfigurator.INSTANCE.configure(this);
    }

    @Override
    public void savePreferences(String userLogin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.prefKeyUser), userLogin);
        editor.apply();
    }

    @Override
    public void setUserAccountData(UserAccount userAccount){
        LoginScreenActivity.userAccount = userAccount;
    }

    @Override
    public void nextScreen() {
        dismissProgress();

        Gson gson = new Gson();
        String jSonUserAccountLogin = gson.toJson(userAccount);

        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("jSonUserAccountLogin", jSonUserAccountLogin);
        startActivity(intent);
    }

    @Override
    public boolean verificarConexao(){
        return VerificaConexao(this);
    }

    @Override
    public void connectionRefused(){
        dismissProgress();
        showAlert(getString(R.string.warningTitle), getString(R.string.connectionRefused));
    }

    @Override
    public void emptyUsername() {
        dismissProgress();
        showAlert(getString(R.string.warningTitle), getString(R.string.emptyUserName));
    }

    @Override
    public void emptyPassword() {
        dismissProgress();
        showAlert(getString(R.string.warningTitle), getString(R.string.emptyPassword));
    }

    @Override
    public void invalidUsername() {
        dismissProgress();
        showAlert(getString(R.string.warningTitle), getString(R.string.invalidUserName));
    }

    @Override
    public void invalidPassword() {
        dismissProgress();
        showAlert(getString(R.string.warningTitle), getString(R.string.invalidPassword));
    }

    @Override
    public void invalidLogin() {
        dismissProgress();
        showAlert(getString(R.string.warningTitle), getString(R.string.invalidLogin));
    }

    @Override
    public void clearPassword(){
        etPassword.setText("");
    }

    private void displayScreenData() {
        String username = sharedPreferences.getString(getString(R.string.prefKeyUser), "");

        etUserName = findViewById(R.id.etLoginUsername);
        etUserName.setText(username);
        etPassword = findViewById(R.id.etLoginPassword);

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(getString(R.string.loadingTitle), getString(R.string.loadingMessage));

                user = new User();
                user.setUsername(etUserName.getText().toString());
                user.setPassword(etPassword.getText().toString());

                LoginScreenRequest request = new LoginScreenRequest();
                request.user = user;

                loginScreenInteractor.doLogin(request);
            }
        });
    }

}
