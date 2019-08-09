package com.example.testesantander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import Models.UserAccount;

public class DetailActivity extends AppCompatActivity {
    private TextView tvSaldo;
    private TextView tvConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setElevation(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.azul));
        }
        Intent intent = getIntent();
        UserAccount userAccount = (UserAccount) intent.getSerializableExtra("userAccount");
        setTitle(userAccount.getName());
        tvSaldo = findViewById(R.id.tvSaldo);
        tvConta = findViewById(R.id.tvConta);
        tvSaldo.setText(String.format("R$ %.2f", userAccount.getBalance()));
        tvConta.setText(String.format("%s / %s", userAccount.getAgency(), userAccount.getBankAccount()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logoff_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logoff) {
            Intent it = new Intent(this, LoginActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}
