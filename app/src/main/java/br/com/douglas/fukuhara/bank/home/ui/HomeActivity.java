package br.com.douglas.fukuhara.bank.home.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.douglas.fukuhara.bank.R;
import br.com.douglas.fukuhara.bank.network.vo.UserAccount;

import static br.com.douglas.fukuhara.bank.utils.LoginUtils.getUserAccountFromBundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        UserAccount userAccount = getUserAccountFromBundle(getIntent());
    }
}
