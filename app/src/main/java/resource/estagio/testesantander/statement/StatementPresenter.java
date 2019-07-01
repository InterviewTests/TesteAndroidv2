package resource.estagio.testesantander.statement;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.testesantander.infra.ApiServices;
import resource.estagio.testesantander.infra.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementPresenter implements StatementContract.Presenter{

    private StatementContract.View view;

    public StatementPresenter(StatementContract.View view) {
        this.view = view;
    }

    @Override
    public void getStatement(long id) {
        Call<StatementResponse> call = RetrofitClient.getInstance()
                .createService(ApiServices.class).getStatementList(id);


        call.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                StatementResponse statementResponse = response.body();
                List<Statement> statements = new ArrayList<Statement>(statementResponse.getStatementList());
                view.showList(statementResponse.getStatementList());

            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}
