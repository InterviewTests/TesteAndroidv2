package com.santander.app.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.santander.app.R;
import com.santander.app.adapter.ListaAdapter;
import com.santander.app.api.Api;
import com.santander.app.bean.Registro;
import com.santander.app.bean.UserAccount;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private Context context;
    private UserAccount account;
    private List<Registro> registros;

    @ViewById(R.id.tvNome)
    TextView tvNome;

    @ViewById(R.id.tvConta)
    TextView tvConta;

    @ViewById(R.id.tvSaldo)
    TextView tvSaldo;

    @ViewById(R.id.btLogout)
    ImageView btLogout;

    @ViewById(R.id.rvRecentes)
    RecyclerView rvRecentes;

    @ViewById(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    @AfterViews
    protected void loadData(){
        if(getIntent().hasExtra("userAccount")){
            account = (UserAccount) getIntent().getSerializableExtra("userAccount");
            if(account != null){
                tvNome.setText(account.getName());
                tvConta.setText(account.getBankAccount() + " / " + account.getAgency());
                tvSaldo.setText("R$ " + String.format("%.2f", account.getBalance()));

                loadLista();
            }
            else{
                Toast.makeText(getApplicationContext(), R.string.erro_login, Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.erro_login, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void loadLista(){

        progress.setVisibility(View.VISIBLE);
        registros = new ArrayList<>();

        Api.get("statements/1", new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progress.setVisibility(View.GONE);



                JSONObject jsonErro = response.optJSONObject("error");
                if (jsonErro.length() == 0) {
                    JSONArray lista = response.optJSONArray("statementList");
                    if(lista != null) {
                        for (int i = 0; i < lista.length(); i++) {
                            JSONObject registro = lista.optJSONObject(i);
                            if (registro != null){
                                Gson gson = new Gson();
                                Registro reg = gson.fromJson(registro.toString(), Registro.class);
                                registros.add(reg);
                            }
                        }

                        ListaAdapter adapter = new ListaAdapter(context, registros);
                        rvRecentes.setLayoutManager(new LinearLayoutManager(context));
                        rvRecentes.setAdapter(adapter);
                    }

                } else {
                    Toast.makeText(context, R.string.erro_lista, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                progress.setVisibility(View.GONE);
                ListaAdapter adapter = new ListaAdapter(context, registros);
                rvRecentes.setAdapter(adapter);
                Toast.makeText(context, R.string.erro_lista, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Click(R.id.btLogout)
    protected void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.pergunta);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });
        builder.setNegativeButton(R.string.NO, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
