package com.example.appbank.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;

import com.example.appbank.R;
import com.example.appbank.ui.adapter.AdapterAccount;

import java.util.List;

public class Account extends AppCompatActivity {

    private ImageButton imageButtonLogout;
    private RecyclerView recyclerViewAccount;
    private List<Account> listAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        imageButtonLogout = findViewById(R.id.imageButtonLogout);
        recyclerViewAccount = findViewById(R.id.recyclerViewAccount);
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

}
