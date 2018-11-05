package br.com.santanderteste.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.santanderteste.R;
import br.com.santanderteste.model.Statement;
import br.com.santanderteste.ui.BaseFragment;
import br.com.santanderteste.ui.activity.MainActivity;
import br.com.santanderteste.ui.adapter.StatementsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StatementsFragment extends BaseFragment {

    private Unbinder unbinder;

    @Nullable
    @BindView(R.id.statement_recyclerview)
    RecyclerView statementRecyclerview;

    private StatementsAdapter statementsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bank_statement_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideActionBar((MainActivity) getActivity());

        statementsAdapter = new StatementsAdapter();
        statementsAdapter.setData(getSampleList());

        statementRecyclerview.setAdapter(statementsAdapter);

        layoutManager = new LinearLayoutManager(getActivity());
        statementRecyclerview.setLayoutManager(layoutManager);


    }

    //TODO: remove this method
    public List<Statement> getSampleList() {
        List<Statement> statements = new ArrayList<>();
        statements.add(getNewStatement("1"));
        statements.add(getNewStatement("2"));
        statements.add(getNewStatement("3"));
        statements.add(getNewStatement("4"));
        statements.add(getNewStatement("5"));

        return statements;
    }

    //TODO: remove this method
    public Statement getNewStatement(String i) {
        Statement statement = new Statement(i, i, i, i);
        return statement;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
