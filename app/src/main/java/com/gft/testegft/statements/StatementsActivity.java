package com.gft.testegft.statements;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gft.testegft.R;
import com.gft.testegft.base.BaseActivity;
import com.gft.testegft.statements.data.Statement;
import com.gft.testegft.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StatementsActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private StatementsViewModel viewModel;

    private StatementsRecyclerViewAdapter adapter;

    private List<Statement> statements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        attachViewModel();
        setupRecycler();
        observe();
    }

    void attachViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StatementsViewModel.class);
        com.gft.testegft.databinding.ActivityStatementesBinding binding = DataBindingUtil.setContentView(this, layoutRes());
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_statementes;
    }

    private void observe() {
        viewModel.getStatements().observe(this, this::onGetStatements);
        viewModel.getErrorMessage().observe(this, this::showToast);
    }

    private void onGetStatements(List<Statement> statements) {
        adapter.statements = statements;
        adapter.notifyDataSetChanged();
    }

    private void showToast(String value) {
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
    }

    public void logout(View v) {
        finish();
    }

    private void setupRecycler() {
        RecyclerView recyclerView = findViewById(R.id.statementRecycleView);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new StatementsRecyclerViewAdapter(statements);
        recyclerView.setAdapter(adapter);
    }
}
