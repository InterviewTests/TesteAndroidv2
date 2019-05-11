package com.example.santanderapp.santander;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.santanderapp.santander.util.Utils;

public class DetailsActivity extends AppCompatActivity {

    private Integer userId;

    private ImageView ivLogout;
    private TextView tvUser;
    private TextView tvAccount;
    private TextView tvBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        configureUI();

        getSharedPreferences();

        ivLogout.setOnClickListener(listenerLogout);
    }

    private void configureUI() {

        ivLogout = findViewById(R.id.ivLogout);
        tvUser = findViewById(R.id.tvUser);
        tvAccount = findViewById(R.id.tvAccount);
        tvBalance = findViewById(R.id.tvBalance);

    }

    private void getSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.userAccount), MODE_PRIVATE);

        userId = preferences.getInt(getString(R.string.userId), 0);
        tvUser.setText(preferences.getString(getString(R.string.name), ""));
        tvAccount.setText(preferences.getString(getString(R.string.bankAccount), "") + " / " + Utils.formatAccount(preferences.getString(getString(R.string.agency), "")));
        tvBalance.setText(Utils.formatReal(String.valueOf(preferences.getFloat(getString(R.string.balance), 0.0f))));

    }

    //Evento de onclick do botão logout
    private View.OnClickListener listenerLogout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
