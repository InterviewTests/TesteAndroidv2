package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import com.br.web.glix.interviewgiovanipaleologo.models.Statement;

import java.lang.ref.WeakReference;
import java.util.List;

interface HomeScreenPresenterInput {
    void showStatementList(HomeScreenResponse homeScreenResponse);
    void showStatementList(List<Statement> statementList);
}


public class HomeScreenPresenter implements HomeScreenPresenterInput {
    public WeakReference<HomeScreenActivityInput> homeScreenActivity;

    @Override
    public void showStatementList(HomeScreenResponse homeScreenResponse) {
        if (homeScreenResponse != null) {
            homeScreenActivity.get().showStatementList(homeScreenResponse);
        }
    }

    public void showStatementList(List<Statement> statementList) {
        homeScreenActivity.get().showStatementList(statementList);
    }
}
