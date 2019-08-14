package br.com.giovanni.testebank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import java.util.concurrent.ExecutionException;

import br.com.giovanni.testebank.Interactor.LoginControlValidation;
import br.com.giovanni.testebank.Model.SetLoginJson;
import br.com.giovanni.testebank.Presenter.UserModel;

public class MainActivity extends AppCompatActivity {

    public EditText getUser;
    public EditText getPassword;
    public Button btnLogin;
    public TextView textViewJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getUser = findViewById(R.id.editTextUserId);
        getPassword = findViewById(R.id.editTextPasswordId);
        btnLogin = findViewById(R.id.buttonLoginId);
        textViewJson = findViewById(R.id.textViewJson);

        btnLoginOnClick();

    }




    public void btnLoginOnClick (){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getLoginAcces();

            }
        });
    }


    public void getLoginAcces (){
        String getUserConvert = getUser.getText().toString();
        String getPasswordConvert = getPassword.getText().toString().trim();
            LoginControlValidation loginControlValidation = new LoginControlValidation(getUserConvert, getPasswordConvert);
            SetLoginJson setJs = new SetLoginJson();
            setJs.getUser(getUserConvert);
            setJs.getPassword(getPasswordConvert);
            WebService webService = new WebService(getUserConvert);


            if (loginControlValidation.loginControlValidation() == true){
                getPassword.setError(null);

                try {
                    UserModel retorno = new WebService(getUserConvert).execute().get();
                    textViewJson.setText(retorno.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                getPassword.setError("Senha Inválida");
            }


    }


}