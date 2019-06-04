package com.project.personal.app_bank.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.project.personal.app_bank.R;
import com.project.personal.app_bank.models.StatementList;


public class CurrencyActivity extends AppCompatActivity {

    private TextView name, bankAccountAgency, balance;
    private Fragment recentesFragment;
    private ImageButton logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        name = findViewById(R.id.textViewName);
        bankAccountAgency = findViewById(R.id.textViewNumConta);
        balance = findViewById(R.id.textViewTotalSaldo);
        logoutButton=(ImageButton)findViewById(R.id.imageButtonLogout);

        //pega as informações da conta
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            name.setText(bundle.getString("userName"));
            bankAccountAgency.setText(bundle.getString("userAccount"));
            balance.setText("R$ "+bundle.getString("userBalance"));
        }

        //inicia o supporte na View
        recentesFragment = new RecentesFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, recentesFragment).commit();


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrencyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
