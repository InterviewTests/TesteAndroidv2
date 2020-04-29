package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import com.br.web.glix.interviewgiovanipaleologo.models.Statement;

import java.util.List;

class HomeScreenViewModel {
    List<Statement> statementList;
}

class HomeScreenRequest {
    String userId;
}

class HomeScreenResponse {
    List<Statement> statementList;
}
