package com.example.santanderapp.santander;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private Integer userId;
    private String name;
    private String bankAccount;
    private String agency;
    private Float balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSharedPreferences();
    }

    private void getSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.userAccount), MODE_PRIVATE);

        userId = preferences.getInt(getString(R.string.userId), 0);
        name = preferences.getString(getString(R.string.name), "");
        bankAccount = preferences.getString(getString(R.string.bankAccount), "");
        agency = preferences.getString(getString(R.string.agency), "");
        balance = preferences.getFloat(getString(R.string.balance), 0.0f);
    }
}
