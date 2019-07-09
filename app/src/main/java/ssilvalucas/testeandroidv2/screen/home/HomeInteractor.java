package ssilvalucas.testeandroidv2.screen.home;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ssilvalucas.testeandroidv2.data.api.StatementsService;
import ssilvalucas.testeandroidv2.data.model.StatementsResponse;
import ssilvalucas.testeandroidv2.data.retrofit.Retrofit;

interface HomeInteractorInput{
    void fetchStatementsById(long id);
}

public class HomeInteractor implements HomeInteractorInput{

    public HomePresenterInput output;

    @Override
    public void fetchStatementsById(long id) {
        StatementsService statementsService = Retrofit.getInstance().create(StatementsService.class);

        statementsService.getStatements(id).enqueue(new Callback<StatementsResponse>() {
            @Override
            public void onResponse(Call<StatementsResponse> call, Response<StatementsResponse> response) {
                if(response.isSuccessful()){
                    output.onSuccessfulFetchStatements(response.body());
                }
            }

            @Override
            public void onFailure(Call<StatementsResponse> call, Throwable t) {
                output.throwError(t.getMessage());
            }
        });
    }
}
