package com.brunorfreitas.teste_ibm_santander.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brunorfreitas.teste_ibm_santander.R;


import com.brunorfreitas.teste_ibm_santander.adapter.AdapterStatements;
import com.brunorfreitas.teste_ibm_santander.helper.Preferencias;
import com.brunorfreitas.teste_ibm_santander.model.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private TextView tv_tbUserName;
    private ImageView iv_sair;
    private TextView tv_conta;
    private TextView tv_saldo;
    private RecyclerView recyclerView;
    private AdapterStatements adapterStatement;
    private List<Statement> listStatements;

    private double userId;

    private Preferencias preferencias;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaVariaves();
        inicializaAcoes();
    }

    @Override
    public void onBackPressed() {
        confirmacaSaida();
    }

    private void inicializaVariaves() {
        context = getBaseContext();
        preferencias = new Preferencias(context);

        tv_tbUserName = findViewById(R.id.main_activity_tb_username);
        iv_sair = findViewById(R.id.main_activity_iv_sair);
        tv_conta = findViewById(R.id.main_activity_tv_conta);
        tv_saldo = findViewById(R.id.main_activity_tv_saldo);

        recyclerView = findViewById(R.id.main_activity_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        listStatements = new ArrayList<>();

        adapterStatement = new AdapterStatements(listStatements, context);
        recyclerView.setAdapter(adapterStatement);


        loadUserInformations();
        loadListStatements();

    }

    private void inicializaAcoes() {

        iv_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmacaSaida();

                Toast.makeText(context, "sair", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirmacaSaida() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(R.string.var_title_getout);
        alerta.setMessage(R.string.var_msg_getout);
        alerta.setCancelable(false);
        alerta.setPositiveButton(R.string.var_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent mIntent = new Intent(context, LoginActivity.class);
                startActivity(mIntent);
                finish();
            }
        });
        alerta.setNegativeButton(R.string.var_no, null);
        //
        alerta.show();
    }

    private void loadUserInformations() {

        userId = getIntent().getLongExtra("id",0);
        String name = getIntent().getStringExtra("name");
        String bankaccount = getIntent().getStringExtra("bankAccount");
        String agency = getIntent().getStringExtra("agency");
        double saldo = getIntent().getDoubleExtra("balance", 0);

        tv_tbUserName.setText(name);

        tv_conta.setText(bankaccount+" / "+formataAgencia(agency));

//        NumberFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
//        String valorFormatado = decimalFormat.format(saldo);
//        tv_saldo.setText(valorFormatado);
        tv_saldo.setText("R$"+String.valueOf(saldo));
    }

    private void loadListStatements() {
        String url = "https://bank-app-test.herokuapp.com/api/statements/"+userId;

        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Toast.makeText(context, R.string.var_erro_conectar_tente_mais_tarde, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myResponse = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(myResponse);
                                JSONArray jsonElements = jsonObject.getJSONArray("statementList");

                                for (int i = 0; i < jsonElements.length(); i++) {

                                    JSONObject jsonObject1 = jsonElements.getJSONObject(i);
                                    Statement statement = new Statement(jsonObject1.getString("title").toString(),
                                            jsonObject1.getString("desc"),
                                            jsonObject1.getString("date"),
                                            jsonObject1.getDouble("value"));
                                    listStatements.add(statement);
                                }
                                adapterStatement.notifyDataSetChanged();


                            }catch (Exception e){
                                e.printStackTrace();

                            }
                        }
                    });

                }else{
                    Toast.makeText(MainActivity.this,R.string.var_erro_conectar, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private String formataAgencia(String agency) {
        return(agency.substring(0, 2) + "."+
                agency.substring(2, 8) + "-" +
                agency.substring(8, 9));
    }
}
