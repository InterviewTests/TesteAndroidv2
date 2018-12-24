package br.com.edrsantos.santandertesteandroidv2.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.edrsantos.santandertesteandroidv2.R;
import br.com.edrsantos.santandertesteandroidv2.adapter.StatementsRecyclerViewAdapter;
import br.com.edrsantos.santandertesteandroidv2.model.Statement;
import br.com.edrsantos.santandertesteandroidv2.util.Util;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String API_STATEMENTS = "https://bank-app-test.herokuapp.com/api/statements/1";

    private TextView txtUserName;
    private TextView txtAccountNumber;
    private TextView txtBalance;
    private ImageView imgLogout;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    List<Statement> statementList;
    StatementsRecyclerViewAdapter statementsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.pbStatements);
        txtUserName = findViewById(R.id.txtUserName);
        txtAccountNumber = findViewById(R.id.txtAccountNumber);
        txtBalance = findViewById(R.id.txtBalance);
        imgLogout = findViewById(R.id.imgLogout);
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onResume() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            txtUserName.setText(bundle.getString("nome"));
            String account = String.format("%s / %s", bundle.getString("bankAccount"), bundle.getString("agency"));
            txtAccountNumber.setText(account);
            txtBalance.setText(String.valueOf(bundle.getDouble("balance")));
        }

        getStatement();
        super.onResume();
    }

    private void getStatement(){
        progressBar.setVisibility(View.VISIBLE);
        statementList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_STATEMENTS)
                .get()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "ba289ff8-7313-451b-aa1b-1ecdf787b166")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            progressBar.setVisibility(View.GONE);
                            Util.showToast(MainActivity.this, "erro: " + e.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
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
                                        Statement statement = new Statement(jsonObject.getString("title").toString(), jsonObject.getString("desc"), jsonObject.getString("date"), jsonObject.getDouble("value"));
                                        statementList.add(statement);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                statementsRecyclerViewAdapter = new StatementsRecyclerViewAdapter(statementList, getApplicationContext());
                                recyclerView.setAdapter(statementsRecyclerViewAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                progressBar.setVisibility(View.GONE);
                                Util.showToast(MainActivity.this, "Erro na conexão!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}
