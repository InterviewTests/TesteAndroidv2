package com.example.appbank.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appbank.R;
import com.example.appbank.ui.adapter.AdapterAccount;

import java.util.List;

public class Account extends AppCompatActivity {

    private ImageButton imageButtonLogout;
    private RecyclerView recyclerViewAccount;
    private List<Account> listAccount;
    private TextView textViewName;
    private TextView textViewConta;
    private TextView textViewCC_Ag;
    private TextView textViewSaldo;
    private TextView textViewMostraSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        loadUi();
        setFonts();

        imageButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, MainActivity.class);
                startActivity(intent);
            }
        });

        AdapterAccount adapter = new AdapterAccount();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewAccount.setLayoutManager(layoutManager);
        recyclerViewAccount.setHasFixedSize(true);
        recyclerViewAccount.setAdapter(adapter);

    }

    private void loadUi(){
        imageButtonLogout = findViewById(R.id.imageButtonLogout);
        recyclerViewAccount = findViewById(R.id.recyclerViewAccount);
        textViewName = findViewById(R.id.textViewName);
        textViewConta = findViewById(R.id.textViewConta);
        textViewCC_Ag = findViewById(R.id.textViewCC_Ag);
        textViewSaldo = findViewById(R.id.textViewSaldo);
        textViewMostraSaldo = findViewById(R.id.textViewMostraSaldo);

    }

    private void setFonts() {
        Typeface normal = Typeface.createFromAsset(getAssets(),
                "HelveticaNeueLight.ttf");

        textViewName.setTypeface(normal);
        textViewConta.setTypeface(normal);
        textViewCC_Ag.setTypeface(normal);
        textViewSaldo.setTypeface(normal);
        textViewMostraSaldo.setTypeface(normal);
    }

}
