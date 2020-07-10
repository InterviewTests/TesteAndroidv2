package com.accenture.android.app.testeandroid.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.accenture.android.app.testeandroid.R;
import com.accenture.android.app.testeandroid.databinding.ActivityMainBinding;
import com.accenture.android.app.testeandroid.domain.Statement;
import com.accenture.android.app.testeandroid.domain.UserAccount;
import com.accenture.android.app.testeandroid.presentation.main.adapters.StatementsRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {
    private final static String TAG = "CustomLog - " + MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;

    private MainContract.Presenter presenter;

    private StatementsRecyclerAdapter statementsRecyclerAdapter;
    private ArrayList<Statement> statements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());

        this.presenter = new MainPresenter(this, this.getLifecycle(), this);

        setContentView(this.binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.configRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.presenter.buscarStatements(1L);
    }

    private void configRecyclerView() {
        LinearLayoutManager lltManager = new LinearLayoutManager(this);
        this.binding.rcvStatements.setLayoutManager(lltManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, lltManager.getOrientation());
        dividerItemDecoration.setDrawable(this.getResources().getDrawable(R.drawable.shape_divider_transparent));
        this.binding.rcvStatements.addItemDecoration(dividerItemDecoration);

        this.statementsRecyclerAdapter = new StatementsRecyclerAdapter(this, this.statements);
        this.binding.rcvStatements.setAdapter(this.statementsRecyclerAdapter);
    }

    @Override
    public void setLoading() {
        this.binding.pgbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void unsetLoading() {
        this.binding.pgbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setContent() {
        this.binding.txtTitulo.setVisibility(View.VISIBLE);
        this.binding.rcvStatements.setVisibility(View.VISIBLE);
    }

    @Override
    public void unsetContent() {
        this.binding.txtTitulo.setVisibility(View.INVISIBLE);
        this.binding.rcvStatements.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setFeedback(String message) {
        Snackbar.make(this.binding.getRoot(), message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setUserAccount(UserAccount userAccount) {
        this.binding.iclAppBar.txtUserName.setText(userAccount.getName());
        this.binding.iclAppBar.txtConta.setText(userAccount.getBankAccount());
        this.binding.iclAppBar.txtSaldo.setText(this.formatarReal(userAccount.getBalance()));
    }

    @Override
    public void setStatements(ArrayList<Statement> statements) {
        this.statements.clear();

        this.statements.addAll(statements);

        this.statementsRecyclerAdapter.notifyDataSetChanged();
    }

    private String formatarReal(Double valor) {
        String formated = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(valor);

        // Verifica se número formatado está entre parenteses
        // Se sim, número é negativo, então formata o número para retirar os parenteses e
        // colocar o '-' na frente.
        if (formated.contains("(")) {
            formated = formated.replaceAll("[()]", "");

            formated = "- " + formated;
        }

        return formated;
    }
}