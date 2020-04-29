package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import com.br.web.glix.interviewgiovanipaleologo.models.Statement;

import java.lang.ref.WeakReference;
import java.util.List;

interface HomeScreenRouterInput {
}

public class HomeScreenRouter implements HomeScreenActivityInput {
    public WeakReference<HomeScreenActivity> activity;

    @Override
    public void showStatementList(HomeScreenResponse homeScreenResponse) {

    }

    @Override
    public void showStatementList(List<Statement> statementList) {

    }
}
