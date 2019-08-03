package com.example.testesantander;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controladores.ControleLogin;
import Modelos.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText etUser;
    private EditText etPassword;
    private ControleLogin controlador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario user = new Usuario(etUser.getText().toString(),
                        etPassword.getText().toString());
                controlador = new ControleLogin(user);
                Toast.makeText(view.getContext(), ""+controlador.validaUsuario(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
