package br.com.giovanni.testebank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import br.com.giovanni.testebank.Interactor.DaoLogin;
import br.com.giovanni.testebank.Interactor.LoginControlValidation;
import br.com.giovanni.testebank.Interactor.LoginRequest;

public class MainActivity extends AppCompatActivity {

    public EditText getUser;
    public EditText getPassword;
    public Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getUser = findViewById(R.id.editTextUserId);
        getPassword = findViewById(R.id.editTextPasswordId);
        btnLogin = findViewById(R.id.buttonLoginId);

        btnLoginOnClick();

    }

    String getUserConvert = getUser.getText().toString();
    String getPasswordConvert = getPassword.getText().toString().trim();


    public void btnLoginOnClick (){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getLoginAcces();

//               String getUserConvert = getUser.getText().toString();
//               String getPasswordConvert = getPassword.getText().toString().trim();



//                if (loginControlValidation.loginControlValidation() == true){
//                    getPassword.setError(null);
//                   // Toast.makeText(getApplicationContext(),"Validado",Toast.LENGTH_LONG).show();
//
//                    try {
//                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
//                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
//                        }
//                        else {
//
//
//                        LoginRequest loginRequest = DaoLogin.loginRequest(getUserConvert,getPasswordConvert);
//                    } catch (JSONException e) { Toast.makeText(this, e.printStackTrace(),Toast.LENGTH_LONG).show(); }
//
//
//                }else {
//                    Toast.makeText(getApplicationContext(),"Não Validado",Toast.LENGTH_LONG).show();
//                    getPassword.setError("Senha Inválida");
//                }
            }
        });
    }


    public void getLoginAcces (){
            LoginControlValidation loginControlValidation = new LoginControlValidation(getUserConvert, getPasswordConvert);

            if (loginControlValidation.loginControlValidation() == true){
                getPassword.setError(null);
                try {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
                        DaoLogin.loginRequest(getUserConvert,getPasswordConvert);
                        //LoginRequest loginRequest = DaoLogin.loginRequest(getUserConvert,getPasswordConvert);
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Não Validado",Toast.LENGTH_LONG).show();
                }
            } else {
                getPassword.setError("Senha Inválida");
            }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

}