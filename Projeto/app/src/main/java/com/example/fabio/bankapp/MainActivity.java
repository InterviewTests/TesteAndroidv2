package com.example.fabio.bankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fabio.bankapp.classes.CustomAdapter;
import com.example.fabio.bankapp.classes.DataRecentes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtnome, txtconta, txtsaldo;
    private ImageView imglogout;
    private RecyclerView rvrecentes;
    private ProgressBar pbarrecentes;
    private CustomAdapter adapter;
    private String valorstr;
    private Locale ptBr = new Locale("pt", "BR");
    private List<DataRecentes> recentesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();

        //Recupera dados do usuário
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        txtnome.setText(Objects.requireNonNull(bundle).getString("nome"));

        valorstr = bundle.getString("agency");
        String conta = null;
        if (valorstr != null) {
            conta = valorstr.substring(0, 2) + "." + valorstr.substring(2, 8) + "-" + valorstr.substring(8, 9);
        }
        txtconta.setText(String.format("%s/%s", bundle.getString("bankAccount"), conta));

        Double saldo = bundle.getDouble("balance");
        valorstr = NumberFormat.getCurrencyInstance(ptBr).format(saldo);
        txtsaldo.setText(valorstr);

        rvrecentes.setHasFixedSize(true);
        rvrecentes.setLayoutManager(new LinearLayoutManager(this));

        recentesList = new ArrayList<>();
        CarregaRecentes(1);

        imglogout.setOnClickListener(this);
    }

    private void Inicializar(){
        imglogout = findViewById(R.id.imgLogout);
        txtnome = findViewById(R.id.txtNome);
        txtconta = findViewById(R.id.txtConta);
        txtsaldo = findViewById(R.id.txtSaldo);
        rvrecentes = findViewById(R.id.rvRecentes);
        pbarrecentes = findViewById(R.id.pBarRecentes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLogout:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void CarregaRecentes(final int id){
        pbarrecentes.setVisibility(View.VISIBLE);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://bank-app-test.herokuapp.com/api/statements/1")
                .get()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "ba289ff8-7313-451b-aa1b-1ecdf787b166")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "Erro ao carregar movimentações", Toast.LENGTH_LONG).show();
                pbarrecentes.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject(myResponse);
                                try {
                                    JSONArray result = json.getJSONArray("statementList");
                                    for(int i=0;i<=result.length();i++)
                                    {
                                        JSONObject jsonObject = result.getJSONObject(i);
                                        DataRecentes dataRecentes = new DataRecentes(jsonObject.getString("title").toString(), jsonObject.getString("desc"), jsonObject.getString("date"), jsonObject.getDouble("value"));
                                        recentesList.add(dataRecentes);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                adapter = new CustomAdapter(getApplicationContext(), recentesList);
                                rvrecentes.setAdapter(adapter);

                                pbarrecentes.setVisibility(View.GONE);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao carregar movimentações", Toast.LENGTH_LONG).show();
                    pbarrecentes.setVisibility(View.GONE);
                }
            }
        });
    }
}
