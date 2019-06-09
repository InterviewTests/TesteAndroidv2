package com.br.projetotestesantanter.refactor.statementScreen;


import java.lang.ref.WeakReference;

interface StatementPresenterInput {
    void responselistStatemntMetaData(StatementModel.ListStatemt response);
    void visibleProgressBar();
    void hideProgressBar();
}

public class StatementPresenter implements  StatementPresenterInput{

    public WeakReference<StatementActivityInput> output;

    @Override
    public void responselistStatemntMetaData(StatementModel.ListStatemt response) {

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
