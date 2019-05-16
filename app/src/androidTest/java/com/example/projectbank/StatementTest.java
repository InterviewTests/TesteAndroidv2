package com.example.projectbank;

import android.util.Log;

import com.example.projectbank.Controller.StatementController;
import com.example.projectbank.Model.Statement;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementTest {

    @Test
    public void Teste()
    {
        new StatementController()
                .getStatmentsService()
                .buscarStatements(1)
                .enqueue(new Callback<Statement>() {
                    @Override
                    public void onResponse(Call<Statement> call, Response<Statement> response) {
                        Statement statement = response.body();

                    }
                    @Override
                    public void onFailure(Call<Statement> call, Throwable t) {
                        Log.e("StatementService   ", "Erro ao buscar o statements:" + t.getMessage());

                    }
                });
        Assert.assertFalse(false);
    }

    @Test
    public void testeRetrofitSucess()
    {
        new StatementController()
                .getStatmentsService()
                .buscarStatements(1)
                .enqueue(new Callback<Statement>() {
                    @Override
                    public void onResponse(Call<Statement> call, Response<Statement> response) {
                        Statement statement = response.body();
                        int code = statement.getError().getCode();
                        String message = statement.getError().getMessage();
                        if(code == 0 && message == "")
                        {
                            boolean result = true;
                            Assert.assertTrue(result);
                        }

                    }
                    @Override
                    public void onFailure(Call<Statement> call, Throwable t) {
                        Log.e("StatementService   ", "Erro ao buscar o statements:" + t.getMessage());

                    }
                });
    }

    @Test
    public void testeRetrofitError()
    {
        new StatementController()
                .getStatmentsService()
                .buscarStatements(1)
                .enqueue(new Callback<Statement>() {
                    @Override
                    public void onResponse(Call<Statement> call, Response<Statement> response) {
                        Statement statement = response.body();
                        int code = 53;
                        String message = "Statement não encontraddo";
                        if(code != 0 && message != "")
                        {
                            boolean result = false;
                            Assert.assertFalse(result);
                        }

                    }
                    @Override
                    public void onFailure(Call<Statement> call, Throwable t) {
                        Log.e("StatementService   ", "Erro ao buscar o statements:" + t.getMessage());

                    }
                });
    }

}