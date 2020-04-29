package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import com.br.web.glix.interviewgiovanipaleologo.Constants;
import com.br.web.glix.interviewgiovanipaleologo.models.Statement;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

interface HomeScreenInteractorInput {
    void getStatementList(HomeScreenRequest homeScreenRequest);
    void showStatementList(HomeScreenResponse homeScreenResponse);
    void showStatementList(List<Statement> statementList);
}

public class HomeScreenInteractor implements HomeScreenInteractorInput {
    HomeScreenPresenterInput homeScreenPresenterInput;

    @Override
    public void getStatementList(final HomeScreenRequest homeScreenRequest){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HomeScreenAPI loginApi = retrofit.create(HomeScreenAPI.class);
        Call<HomeScreenResponse> call = loginApi.fetchStatements(homeScreenRequest.userId);
        call.enqueue(new Callback<HomeScreenResponse>() {
            @Override
            public void onResponse(Call<HomeScreenResponse> call, Response<HomeScreenResponse> response) {
                homeScreenPresenterInput.showStatementList(response.body());
            }

            @Override
            public void onFailure(Call<HomeScreenResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void showStatementList(HomeScreenResponse homeScreenResponse) {
        homeScreenPresenterInput.showStatementList(homeScreenResponse);
    }

    public void showStatementList(List<Statement> statementList) {
        homeScreenPresenterInput.showStatementList(statementList);
    }
}
