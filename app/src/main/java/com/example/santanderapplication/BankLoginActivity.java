package com.example.santanderapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.santanderapplication.activity.BankCurrencyActivity;

public class BankLoginActivity extends AppCompatActivity {

    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bank_login );

    Login=findViewById( R.id.button_login );
    Login.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent( BankLoginActivity.this ,
                    BankCurrencyActivity.class );
            startActivity( intent );
        }
    } );

    }
}
