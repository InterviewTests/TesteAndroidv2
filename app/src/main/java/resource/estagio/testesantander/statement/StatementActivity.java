package resource.estagio.testesantander.statement;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import resource.estagio.testesantander.R;
import resource.estagio.testesantander.login.LoginActivity;
import resource.estagio.testesantander.model.Statement;
import resource.estagio.testesantander.domain.User;
import resource.estagio.testesantander.statement.adapter.AdapterStatement;


public class StatementActivity extends AppCompatActivity implements StatementContract.View {

    public static final String Helvetica_Neue_Light = "HelveticaNeue-Light.otf";

    private RecyclerView recyclerView;
    private AdapterStatement adapter;

    private StatementContract.Presenter presenter;

    private Dialog dialog;
    private Button dialog_yes, dialog_no;
    private ImageView ic_exit;
    private TextView nameUser, nameSaldo, nameBalance, nameCount, nameBankAccount, nameRecente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        nameUser = findViewById(R.id.nameUser);
        nameSaldo = findViewById(R.id.nameSaldo);
        nameBalance = findViewById(R.id.nameBalance);
        nameCount = findViewById(R.id.nameCount);
        nameBankAccount = findViewById(R.id.nameBankAccount);
        nameRecente = findViewById(R.id.textRecentes);
        ic_exit = findViewById(R.id.ic_exit);


        presenter = new StatementPresenter(this);//inicializa a presenter


        if (getIntent().hasExtra("user")) {
            User user = (User) getIntent().getSerializableExtra("user");

            nameUser.setText(user.getName());
            nameBankAccount.setText(user.getBankAccount() + " / " + user.getAgency()
                    .replaceAll("([0-9]{2})([0-9]{6})([0-9])", "$1.$2-$3"));//regex para a formatação do numero de conta

            Locale locale = new Locale("pt", "BR");//formata a moeda para a moeda local BR
            NumberFormat format = NumberFormat.getCurrencyInstance(locale);
            String currency = format.format(user.getBalance());
            nameBalance.setText(currency);
            presenter.getStatement(user.getUserId());
            setFonts();// implementa a fonte externa implementada na pasta "Assets"
        }
        ic_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showDialogExit();
            }
        });

    }


    private void setFonts() {
        Typeface font = Typeface.createFromAsset(getAssets(), Helvetica_Neue_Light);
        nameUser.setTypeface(font);
        nameBankAccount.setTypeface(font);
        nameBalance.setTypeface(font);
        nameSaldo.setTypeface(font);
        nameCount.setTypeface(font);
        nameRecente.setTypeface(font);
    }

    public void configAdapter(List<Statement> statementList) {//configurar o adapter para o recyclerView
        adapter = new AdapterStatement(statementList);
        recyclerView = findViewById(R.id.recyclerViewStatements);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public Context getActivity() {
        return this;
    }//pega o contexto da activity

    @Override
    public void showList(List<Statement> statementList) {
        configAdapter(statementList);
    }

    @Override
    public void navigateToLogin() {//intent para a tela de login
        Intent intent = new Intent(StatementActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
