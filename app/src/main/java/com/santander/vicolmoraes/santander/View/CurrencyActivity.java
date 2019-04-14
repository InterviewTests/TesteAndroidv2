package com.santander.vicolmoraes.santander.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.santander.vicolmoraes.santander.Model.DataTransacaoVO;
import com.santander.vicolmoraes.santander.Model.TransacaoVO;
import com.santander.vicolmoraes.santander.Model.UsuarioVO;
import com.santander.vicolmoraes.santander.R;
import com.santander.vicolmoraes.santander.ViewModel.RetrofitConfig;
import com.santander.vicolmoraes.santander.ViewModel.TransacoesAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyActivity extends AppCompatActivity {

    private static final String USUARIO_LOGADO = "usuario_logado";

    private UsuarioVO usuarioVO;
    private ArrayList<TransacaoVO> listaTransacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        obterExtras();
        obterTransacoes();
    }

    private void obterTransacoes() {
        Call<DataTransacaoVO> call = new RetrofitConfig().getTransacoes().buscarTransacoes(String.valueOf(usuarioVO.getUserId()));
        call.enqueue(new Callback<DataTransacaoVO>() {
            @Override
            public void onResponse(@NonNull Call<DataTransacaoVO> call, @NonNull Response<DataTransacaoVO> response) {
                DataTransacaoVO data = response.body();
                assert data != null;
                listaTransacoes = data.getTransacoes();
                iniciarViews();
            }

            @Override
            public void onFailure(@NonNull Call<DataTransacaoVO> call, @NonNull Throwable t) {
                Log.e(getString(R.string.erro), getString(R.string.currency_erro) + t.getMessage());
            }
        });
    }

    private void obterExtras() {
        usuarioVO = (UsuarioVO) getIntent().getSerializableExtra(USUARIO_LOGADO);
    }

    private void iniciarViews() {
        if (listaTransacoes == null)
            listaTransacoes = new ArrayList<>();
        TransacoesAdapter adapter = new TransacoesAdapter(listaTransacoes);
        RecyclerView rvTransacoes = findViewById(R.id.rv_currency_transacoes);
        rvTransacoes.setAdapter(adapter);

        TextView tvConta = findViewById(R.id.tv_currency_conta);
        TextView tvNome = findViewById(R.id.tv_currency_nome);
        TextView tvSaldo = findViewById(R.id.tv_currency_saldo);
        Button btSair = findViewById(R.id.bt_currency_sair);

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvSaldo.setText(NumberFormat.getCurrencyInstance().format(usuarioVO.getBalance()));
        tvNome.setText(usuarioVO.getName());
        String contaAgenciaFormatado = usuarioVO.getBankAccount() + " / " + usuarioVO.getAgency();
        tvConta.setText(contaAgenciaFormatado);
    }
}
