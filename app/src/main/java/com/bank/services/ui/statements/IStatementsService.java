package com.bank.services.ui.statements;

import com.bank.services.ui.statements.domain.model.StatementList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IStatementsService {

   public static final String URL_STATEMENT = "https://bank-app-test.herokuapp.com/api/statements/";

   @GET("1")
   Call<StatementList> ListStatement() ;

}
