package com.br.rafael.TesteAndroidv2.ui.statementScreen;

import com.br.rafael.TesteAndroidv2.data.model.StatementResponse;

import java.lang.ref.WeakReference;

interface StatementPresenterInput {
    void responselistStatemntMetaData(StatementResponse response);
    void visibleProgressBar();
    void hideProgressBar();
}

public class StatementPresenter  implements  StatementPresenterInput{

    public WeakReference<StatementActivityInput> output;


    @Override
    public void responselistStatemntMetaData(StatementResponse response) {

        output.get().responselistStatemnt(response);
    }

    @Override
    public void visibleProgressBar() {

        output.get().showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        output.get().hideProgressBar();
    }
}
