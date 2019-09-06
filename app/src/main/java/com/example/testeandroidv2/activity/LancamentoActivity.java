package com.example.testeandroidv2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.adapter.Adapter;
import com.example.testeandroidv2.helper.UsuarioDAO;
import com.example.testeandroidv2.model.Lancamento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LancamentoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Lancamento> listaLancamentos = new ArrayList<>();
    private String listaContas = "https://bank-app-test.herokuapp.com/api/statements/1";
    private TextView textNome;
    private TextView textDadosBancarios;
    private TextView textSaldo;
    private Button btnLogout;
    private ProgressBar progressBarLancamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento);

        textNome = findViewById(R.id.textNome);
        textDadosBancarios = findViewById(R.id.textDadosBancarios);
        textSaldo = findViewById(R.id.textSaldo);
        btnLogout = findViewById(R.id.btnLogout);
        progressBarLancamento = findViewById(R.id.progressBarLancamento);
        progressBarLancamento.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.reciclerView);

        // GRID
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        btnLogout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                logout();
            }

        });

    }

    @Override
    protected void onStart() {

        super.onStart();

        try {

            Bundle dados = getIntent().getExtras();
            Integer userId = Integer.valueOf(dados.getString("userId"));

            this.retornaDadosUsuario(userId);
            this.criarContas();

            Adapter adapter = new Adapter(listaLancamentos);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void retornaDadosUsuario(Integer userId) throws JSONException {

        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        JSONObject dadosUsuario = (JSONObject) usuarioDAO.buscar(userId);

        String nome = dadosUsuario.getString("name");
        String agency = dadosUsuario.getString("agency");
        String bankAccount = String.format("%9s", dadosUsuario.getString("bankAccount")).replace(' ', '0');

        String bankAccountFormated = bankAccount.substring(0, 2)+'.'+bankAccount.substring(2, 7)+'-'+bankAccount.substring(8, 9);
        textNome.setText(nome);
        textDadosBancarios.setText(agency + " / " + bankAccountFormated);

    }

    public void criarContas() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(listaContas)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful()) {

                    final String jsonData = response.body().string();
                    LancamentoActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Lancamento l = new Lancamento();
                            try {

                                JSONObject Jobject = new JSONObject(jsonData);
                                JSONArray a_json = Jobject.getJSONArray("statementList");
                                Float saldo = Float.valueOf(0);

                                for (int i = 0; i < a_json.length(); i++) {

                                    JSONObject object = a_json.getJSONObject(i);

                                    String title = object.getString("title");
                                    String desc = object.getString("desc");
                                    String date = object.getString("date");
                                    String value = object.getString("value");

                                    // Formatar data
                                    String[] a_date = date.split("-");
                                    String newDate = a_date[2] + "/" + a_date[1] + "/" + a_date[0];


                                    l = new Lancamento(title, desc, newDate, formatMoeda(Float.parseFloat(value)));
                                    listaLancamentos.add(l);

                                    // Calcular saldo
                                    Float v = Float.parseFloat(value);
                                    saldo += v;

                                }


                                textSaldo.setText(formatMoeda(saldo));
                                progressBarLancamento.setVisibility(View.GONE);

                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }

            }
        });

    }

    public String formatMoeda(Float valor) {

        // Formatar valor
        BigDecimal dado = new BigDecimal (valor);
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);
        String valorFormatado = nf.format (dado);

        return valorFormatado;

    }

    public void logout() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }
}
