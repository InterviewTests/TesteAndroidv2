package com.testeandroidv2.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.testeandroidv2.App;
import com.testeandroidv2.R;
import com.testeandroidv2.repository.response.Error;

public class ErrorActivity extends AppCompatActivity {

    private TextView txtTitleValue;
    private TextView txtMessageValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_activity);

        txtTitleValue = findViewById(R.id.txtTitleValue);
        txtMessageValue = findViewById(R.id.txtErrorMessageValue);

        loadError();
    }

    private void loadError(){

        Error error = App.error;

        txtTitleValue.setText(error.getCode());
        txtMessageValue.setText(error.getMessage());
    }
}
