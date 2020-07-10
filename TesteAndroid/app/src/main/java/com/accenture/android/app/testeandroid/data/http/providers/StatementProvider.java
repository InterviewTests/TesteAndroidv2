package com.accenture.android.app.testeandroid.data.http.providers;

import android.util.Log;

import com.accenture.android.app.testeandroid.data.converters.StatementConverter;
import com.accenture.android.app.testeandroid.data.http.helpers.StatusCode;
import com.accenture.android.app.testeandroid.data.http.providers.generics.BaseProvider;
import com.accenture.android.app.testeandroid.data.http.resources.StatementResource;
import com.accenture.android.app.testeandroid.data.http.responses.StatementResponse;
import com.accenture.android.app.testeandroid.data.http.responses.generics.ErrorResponse;
import com.accenture.android.app.testeandroid.domain.Statement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class StatementProvider extends BaseProvider {
    private StatementResource resource;

    private Call<StatementResponse> callBuscarStatements;

    public StatementProvider(String baseUrl) {
        super(baseUrl);

        this.resource = this.retrofit.getStatementResource();
    }

    @Override
    public void finish() {
        if (this.callBuscarStatements != null) {
            this.callBuscarStatements.cancel();
        }
    }

    public void buscarStatements(final ExpectedResponseStatements callback, Long userId) {
        this.callBuscarStatements = this.resource.getStatements(userId);
        this.callBuscarStatements.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                if (response.code() == StatusCode.StatusCodeEnum.OK.value) {
                    ErrorResponse error = response.body().getError();

                    if (error.getStatusCode() == null) {
                        ArrayList<Statement> statements = StatementConverter.toDomain(new ArrayList<>(response.body().getData()));

                        callback.onSuccess("Busca efetuada com sucesso.", statements);
                    } else {
                        callback.onFailure(error);
                    }
                } else {
                    callback.onFailure(new ErrorResponse(response.code(), response.message()));
                }
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    Log.e(TAG, "buscarStatements: Requisição cancelada.");
                } else {
                    callback.onFailure(new ErrorResponse(0, t.getMessage()));
                }
            }
        });
    }

    public interface ExpectedResponseStatements {
        void onSuccess(String message, List<Statement> statements);

        void onFailure(ErrorResponse error);
    }
}
