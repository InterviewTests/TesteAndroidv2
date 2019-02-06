package com.luizroseiro.testeandroidv2.mainScreen;

import com.luizroseiro.testeandroidv2.models.StatementModel;
import com.luizroseiro.testeandroidv2.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

interface StatementsPresenterInput {
    void presentStatementsMetaData(StatementsResponse response);
}

public class StatementsPresenter implements StatementsPresenterInput {

    private WeakReference<MainFragmentInput> output;

    StatementsPresenter() {
        this.output = new WeakReference<MainFragmentInput>(MainFragment.getMainFragment());
    }

    @Override
    public void presentStatementsMetaData(StatementsResponse response) {

        List<StatementModel> statements = new ArrayList<>();
        for (Statement statement : response.getStatements()) {
            StatementModel statementModel = new StatementModel();
            statementModel.setTitle(statement.getTitle());
            statementModel.setDesc(statement.getDesc());
            statementModel.setDate(Utils.formatDate(statement.getDate()));
            statementModel.setValue(statement.getValue());

            statements.add(statementModel);
        }

        output.get().displayStatementsMetaData(statements);

    }

}
