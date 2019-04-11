package com.santander.vicolmoraes.santander.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.santander.vicolmoraes.santander.Model.DataUsuarioVO;
import com.santander.vicolmoraes.santander.Model.UsuarioVO;
import com.santander.vicolmoraes.santander.R;
import com.santander.vicolmoraes.santander.ViewModel.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends AppCompatActivity {

    private static final String USUARIO_LOGADO = "usuario_logado";


    private Button btnLogin;
    private EditText etLogin;
    private EditText etSenha;
    private UsuarioVO usuarioVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        iniciarViews();
    }

    private void iniciarViews() {
        btnLogin = findViewById(R.id.bt_location_logar);
        etLogin = findViewById(R.id.et_location_login);
        etSenha = findViewById(R.id.et_location_senha);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<DataUsuarioVO> call = new RetrofitConfig().getUsuario().buscarUsuario(etLogin.getText().toString(), etSenha.getText().toString());
                call.enqueue(new Callback<DataUsuarioVO>() {
                    @Override
                    public void onResponse(Call<DataUsuarioVO> call, Response<DataUsuarioVO> response) {
                        DataUsuarioVO data = response.body();
                        usuarioVO = data.getUserAccount();
                        Intent novaTela = new Intent(getBaseContext(), CurrencyActivity.class);
                        novaTela.putExtra(USUARIO_LOGADO, usuarioVO);
                        startActivity(novaTela);
                    }

                    @Override
                    public void onFailure(Call<DataUsuarioVO> call, Throwable t) {
                        Log.e("CEPService   ", "Erro eRROOOOOOOOOO:" + t.getMessage());
                    }
                });
            }
        });
    }
}
