package com.example.testesantander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Interactors.DetalheInteractor;
import Models.Detalhe;
import Models.UserAccount;
import Presenters.DetalhePresenter;
import Services.ListaDetalhesAdapter;

// TODO: criar classe só para configurar a activity
public class DetalheActivity extends AppCompatActivity implements DetalheActivityInput {
    private TextView tvSaldo;
    private TextView tvConta;
    private RecyclerView listaDetalhes;
    private DetalheInteractor interactor;
    private DetalhePresenter presenter;

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
        listaDetalhes = findViewById(R.id.rvDetalhes);
        tvSaldo = findViewById(R.id.tvSaldo);
        tvConta = findViewById(R.id.tvConta);
        tvSaldo.setText(String.format("R$%.2f", userAccount.getBalance()));
        tvConta.setText(String.format("%s / %s", userAccount.getAgency(), userAccount.getBankAccount()));

        presenter = new DetalhePresenter(this);
        interactor = new DetalheInteractor(presenter);
        interactor.getDetalhes(userAccount.getUserId());
        // mock
//        ArrayList<Detalhe> det = new ArrayList<Detalhe>();
//
//        det.add(new Detalhe("Pagamento", "Conta Vivo", "09/08/2019", 49.99));
//        det.add(new Detalhe("Pagamento1", "Conta Vivo", "09/08/2019", 49.99));
//        det.add(new Detalhe("Pagamento3", "Conta Vivo", "09/08/2019", 49.99));
//        det.add(new Detalhe("Pagamento2", "Conta Vivo", "09/08/2019", 49.99));
//        det.add(new Detalhe("Pagamento4", "Conta Vivo", "09/08/2019", 49.99));
//        det.add(new Detalhe("Pagamento5", "Conta Vivo", "09/08/2019", 49.99));
//        det.add(new Detalhe("Pagamento3", "Conta Vivo", "09/08/2019", 49.99));
//        det.add(new Detalhe("Pagamento2", "Conta Vivo", "09/08/2019", 49.99));
//        det.add(new Detalhe("Pagamento4", "Conta Vivo", "09/08/2019", 49.99));
//        det.add(new Detalhe("Pagamento5", "Conta Vivo", "09/08/2019", 49.99));
//        List<Detalhe> lista = (List<Detalhe>) det;

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

    @Override
    public void injetarDependencia(List<Detalhe> lista) {
        listaDetalhes.setAdapter(new ListaDetalhesAdapter(this, lista));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listaDetalhes.setLayoutManager(manager);
    }
}
