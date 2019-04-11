package com.example.santander.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.santander.R;
import com.example.santander.model.userAccountVO;


public class ContaActivity extends AppCompatActivity {

    private static String EXTRA_USER_ACCOUNT = "EXTRA_USER_ACCOUNT";
    private userAccountVO userAccountVO;
    private TextView nomeUsuario;
    private TextView numeroConta;
    private TextView valorSaldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);
        initVar();
        initVal();
    }

    void initVar() {
        nomeUsuario = findViewById(R.id.tv_conta_nome_usuario);
        numeroConta = findViewById(R.id.tv_conta_numero_conta);
        valorSaldo = findViewById(R.id.tv_conta_valor_saldo);
        ImageButton imageButton = findViewById(R.id.ib_login_botao_sair);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userAccountVO = (userAccountVO) getIntent().getSerializableExtra(EXTRA_USER_ACCOUNT);
    }

    void initVal() {
        nomeUsuario.setText(userAccountVO.getNome());
        numeroConta.setText(userAccountVO.getAgencia());
        valorSaldo.setText(String.valueOf(userAccountVO.getBalanco()));

    }
}
