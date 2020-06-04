package com.gft.testegft.statements;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gft.testegft.R;
import com.gft.testegft.base.BaseActivity;
import com.gft.testegft.databinding.ActivityStatementesBinding;
import com.gft.testegft.statements.data.Statement;
import com.gft.testegft.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StatementsActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private StatementsViewModel viewModel;

    private ActivityStatementesBinding binding;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        attachViewModel();
        setupRecycler();
    }

    void attachViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StatementsViewModel.class);
        binding = DataBindingUtil.setContentView(this, layoutRes());
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_statementes;
    }

    private void setupRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.statementRecycleView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        List<Statement> list = new ArrayList<>();

        list.add((new Statement("Title", "Desc", "Date", 123)));
        list.add((new Statement("Pagamento", "Conta de Luz", "28/05/2020", 132.51)));

        adapter = new StatementsRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
