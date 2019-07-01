package resource.estagio.testesantander.statement;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import resource.estagio.testesantander.R;
import resource.estagio.testesantander.model.User;
import resource.estagio.testesantander.statement.adapter.AdapterStatement;

public class StatementActivity extends AppCompatActivity implements StatementContract.View {
    private RecyclerView recyclerView;
    private List<Statement> statements;
    private AdapterStatement adapter;

    private StatementContract.Presenter presenter;

    TextView nameUser, nameSaldo, nameBalance, nameCount, nameBankAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        nameUser = findViewById(R.id.nameUser);
        nameSaldo = findViewById(R.id.nameSaldo);
        nameBalance = findViewById(R.id.nameBalance);
        nameCount = findViewById(R.id.nameCount);
        nameBankAccount = findViewById(R.id.nameBankAccount);

        presenter = new StatementPresenter(this);




        if(getIntent().hasExtra("user")){
            User user = (User) getIntent().getSerializableExtra("user");

            nameUser.setText(user.getName());
            nameBankAccount.setText(user.getBankAccount() + " / " + user.getAgency()
                    .replaceAll("([0-9]{2})([0-9]{6})([0-9])", "$1.$2-$3"));

            Locale locale = new Locale("pt", "BR");
            NumberFormat format = NumberFormat.getCurrencyInstance(locale);
            String currency = format.format(user.getBalance());
            nameBalance.setText(currency);
            presenter.getStatement(user.getUserId());


        }

    }
    public void configAdapter(List<Statement> statementList){
        adapter = new AdapterStatement( statementList);
        recyclerView = findViewById(R.id.recyclerViewStatements);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }




    @Override
    public Context getActivity() { return this; }

    @Override
    public void showList(List<Statement> statementList) {
        configAdapter(statementList);
    }
}
