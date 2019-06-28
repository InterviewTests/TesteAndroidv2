package com.example.santandertestebank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.santandertestebank.R;
import com.example.santandertestebank.model.models.ObjectsStatements;
import com.example.santandertestebank.model.models.UserAccountLogin;
import com.example.santandertestebank.model.service.ApiService;
import com.example.santandertestebank.ui.adapter.AdapterBankPayments;
import com.example.santandertestebank.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BankPaymentsActivity extends AppCompatActivity implements BankPaymentsContract.View {

    private TextView textViewName;
    private TextView textViewBankAccount;
    private TextView textViewAccountBalance;
    private ImageView imageViewLogout;

    private RecyclerView recyclerViewPayments;
    private AdapterBankPayments adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_bank_payments);
        getSupportActionBar ().hide ();

        loadUI ();
        bringList ();
        showUserInfos ();
        imageViewLogout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                logoutApp ();
            }
        });
    }

    private void showUserInfos() {
        UserAccountLogin userAccount =
                (UserAccountLogin) getIntent ().getSerializableExtra ("keyLogin");

        textViewName.setText (userAccount.getName ());
        textViewBankAccount.setText (userAccount.getBankAccount ());
        textViewAccountBalance.setText (String.valueOf (userAccount.getAccountBalance ()));
    }

    public void bringList() {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl (ApiService.BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();

        ApiService service = retrofit.create (ApiService.class);
        final Call<ObjectsStatements> requestStatements = service.bringStatements (3);

        requestStatements.enqueue (new Callback<ObjectsStatements> () {
            @Override
            public void onResponse(Call<ObjectsStatements> call, Response<ObjectsStatements> response) {
                if (!response.isSuccessful ()) {
                    Toast.makeText (getApplicationContext (), "Erro: " + response.code (),
                            Toast.LENGTH_LONG).show ();
                } else {

                    ObjectsStatements statements = response.body ();

                    if (statements != null) {
                        adapter = new AdapterBankPayments (statements.getPaymentsStatements ());
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                            (getApplicationContext ());

                    recyclerViewPayments.setLayoutManager (layoutManager);
                    recyclerViewPayments.setHasFixedSize (true);
                    recyclerViewPayments.setAdapter (adapter);
                }
            }

            @Override
            public void onFailure(Call<ObjectsStatements> call, Throwable t) {
                Toast.makeText (getApplicationContext (), "Erro: " + t.getMessage (),
                        Toast.LENGTH_LONG).show ();
            }
        });


    }

    private void logoutApp() {
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity (intent);
        finish ();
    }

    private void loadUI() {
        textViewName = findViewById (R.id.text_view_name);
        textViewBankAccount = findViewById (R.id.text_view_bank_account);
        textViewAccountBalance = findViewById (R.id.text_view_account_balance);
        imageViewLogout = findViewById (R.id.image_view_logout);
        recyclerViewPayments = findViewById (R.id.recycler_view_payments);
    }

}