package com.santander.vicolmoraes.santander.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.santander.vicolmoraes.santander.Model.DataTransacaoVO;
import com.santander.vicolmoraes.santander.Model.TransacaoVO;
import com.santander.vicolmoraes.santander.Model.UsuarioVO;
import com.santander.vicolmoraes.santander.R;
import com.santander.vicolmoraes.santander.ViewModel.RetrofitConfig;
import com.santander.vicolmoraes.santander.ViewModel.TransacoesAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyActivity extends AppCompatActivity {
    private static final String USUARIO_LOGADO = "usuario_logado";

    private TextView tvNome;
    private TextView tvConta;
    private TextView tvSaldo;
    private UsuarioVO usuarioVO;
    private RecyclerView rvTransacoes;
    private TransacoesAdapter adapter;
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
            public void onResponse(Call<DataTransacaoVO> call, Response<DataTransacaoVO> response) {
                DataTransacaoVO data = response.body();
                listaTransacoes = data.getTransacoes();
                iniciarViews();
            }

            @Override
            public void onFailure(Call<DataTransacaoVO> call, Throwable t) {
                Log.e("CEPService   ", "Erro eRROOOOOOOOOO:" + t.getMessage());
            }
        });
    }

    private void obterExtras() {
        usuarioVO = (UsuarioVO) getIntent().getSerializableExtra(USUARIO_LOGADO);
        Log.e("CEPService   ", "Erro eRROOOOOOOOOO:" + String.valueOf(usuarioVO.getUserId()));
    }

    private void iniciarViews() {
        if (listaTransacoes == null)
            listaTransacoes = new ArrayList<>();
        adapter = new TransacoesAdapter(listaTransacoes);
        rvTransacoes = findViewById(R.id.rv_currency_transacoes);
        rvTransacoes.setAdapter(adapter);

        tvConta = findViewById(R.id.tv_currency_conta);
        tvNome = findViewById(R.id.tv_currency_nome);
        tvSaldo = findViewById(R.id.tv_currency_saldo);

        tvSaldo.setText(String.valueOf(usuarioVO.getBalance()));
        tvNome.setText(usuarioVO.getName());
        tvConta.setText(usuarioVO.getBankAccount());
    }
}
