package com.riso.zup.bank.StatementScreen;

import com.riso.zup.bank.models.Statement;

import java.util.List;

public interface StatementInteractor {

    interface View{

        void receiveListStatement(List<Statement> listStatement);

        void loadError(int message);
    }

    interface Presenter{

        void getListStatement(int id);

    }
}
