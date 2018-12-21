package teste.claudio.com.testsantander;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.support.v7.widget.LinearLayoutManager;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import android.util.Log;

import android.graphics.Rect;


public class Currency extends AppCompatActivity {

    private Integer userId;
    private String nameId;
    private String bankAccount;
    private String agencyId;
    private Double balanceId;

    private TextView name;
    private TextView bank;
    private TextView balance;
    private ImageView imagem;

    private RecyclerView recyclerView;
    private ArrayList<statementList> data;
    private DataAdapter adapter;

    private DecimalFormat nf = new DecimalFormat("#,###.00");
    SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");

    private Integer VERTICAL_ITEM_SPACE = 40;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        //Traz as informaçoes do activity anterior (livros)
        Bundle extra = getIntent().getExtras();

        if(extra != null){
            userId = extra.getInt("userIdRetorno");
            nameId = extra.getString("nameRetorno");
            bankAccount = extra.getString("bankAccountRetorno");
            agencyId = extra.getString("agencyRetorno");
            balanceId = extra.getDouble("balanceRetorno");

            name = (TextView) findViewById(R.id.txtNome);
            bank = (TextView) findViewById(R.id.txtAgenciaConta);
            balance = (TextView) findViewById(R.id.txtSaldo);
            imagem = (ImageView) findViewById(R.id.imagemLogout);


            name.setText(nameId);
            bank.setText(bankAccount + " / "+ agencyId.toString() );
            balance.setText("R$ " + nf.format(balanceId));

            //Tratar o evento de logout
            imagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fecha a tela atual
                    Currency.this.finish();
                }
            });

            recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);


            //add ItemDecoration
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));


            loadJSON();

        }
    }

    //Carga do Json
    private void loadJSON() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        RetrofitService request = retrofit.create(RetrofitService.class);

        Call<JSONResponse> call = request.statements(userId);

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                int statuscode = response.code();

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getStatementList()));
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }

}
