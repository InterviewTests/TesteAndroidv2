package com.gft.testegft.statements;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gft.testegft.R;
import com.gft.testegft.base.BaseActivity;
import com.gft.testegft.statements.data.Statement;

import java.util.ArrayList;
import java.util.List;

public class StatementsActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupRecycler();
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
