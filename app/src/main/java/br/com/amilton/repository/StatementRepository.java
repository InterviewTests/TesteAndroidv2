package br.com.amilton.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import br.com.amilton.retrofit.RetrofitInstance;
import br.com.amilton.retrofit.WebService;
import br.com.amilton.model.Statement;
import br.com.amilton.model.Statements;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementRepository {
    private final String TAG = StatementRepository.class.getSimpleName();
    private final WebService webService = RetrofitInstance.geWebService();

    public LiveData<List<Statement>> getStatements(String userId) {
        final MutableLiveData<List<Statement>> data = new MutableLiveData<>();

        webService.statements(userId).enqueue(new Callback<Statements>() {
            @Override
            public void onResponse(@NonNull Call<Statements> call, @NonNull Response<Statements> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    Statements statements = response.body();
                    if (statements != null && (statements.getError() == null || statements.getError().size() == 0)) {
                        List<Statement> statement = statements.getStatements();
                        if (statement != null) {
                            data.postValue(statement);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Statements> call, @NonNull Throwable t) {
                data.postValue(new ArrayList<>());
                Log.e(TAG, t.getMessage(), t);
            }
        });

        return data;
    }
}
