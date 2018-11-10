package com.renanferrari.testeandroidv2.application.ui.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.renanferrari.testeandroidv2.R;
import com.renanferrari.testeandroidv2.application.common.utils.Navigator;
import com.renanferrari.testeandroidv2.application.ui.statements.StatementsActivity;

public class LoginActivity extends AppCompatActivity {

  private MaterialButton loginButton;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    loginButton = findViewById(R.id.login_button);
    loginButton.setOnClickListener(
        v -> Navigator.of(this).clearTask().fade().go(StatementsActivity.class));
  }
}