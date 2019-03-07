package br.com.kakobotasso.testeandroidv2.currency;

import android.content.SharedPreferences;

import br.com.kakobotasso.testeandroidv2.util.SharedPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface CurrencyInteractorInput {
    void fetchCurrencyData(int userId);

    void destroyPreferences();
}

public class CurrencyInteractor implements CurrencyInteractorInput {
    public CurrencyPresenterInput output;
    private CurrencyWorkerInput currencyWorkerInput;

    public CurrencyWorkerInput getCurrencyWorkerInput() {
        if(currencyWorkerInput == null) {
            return new CurrencyWorker();
        }

        return currencyWorkerInput;
    }

    public void setCurrencyWorkerInput(CurrencyWorkerInput currencyWorkerInput) {
        this.currencyWorkerInput = currencyWorkerInput;
    }

    @Override
    public void fetchCurrencyData(int userId) {
        currencyWorkerInput = getCurrencyWorkerInput();
        final CurrencyResponse currencyResponse = new CurrencyResponse();

        currencyWorkerInput.getCurrencyInfo(userId, new Callback<CurrencyModel>() {
            @Override
            public void onResponse(Call<CurrencyModel> call, Response<CurrencyModel> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        CurrencyModel body = response.body();
                        populateCurrencyResponse(currencyResponse, body);
                        output.showCurrencyInfo(currencyResponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<CurrencyModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void destroyPreferences() {
        SharedPreferences sharedPrefs = SharedPrefs.getSharedPrefs(output.getContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.remove(SharedPrefs.NAME);
        editor.remove(SharedPrefs.AGENCY);
        editor.remove(SharedPrefs.BANK_ACCOUNT);
        editor.remove(SharedPrefs.USER_ID);
        editor.remove(SharedPrefs.BALANCE);

        editor.commit();
    }

    private void populateCurrencyResponse(CurrencyResponse currencyResponse, CurrencyModel body) {
        if(body.hasErrors()) {
            CurrencyResponse.Errors error = currencyResponse.getError();
            error.setMsg(body.getErrorMessage());
            currencyResponse.setError(error);
        }

        currencyResponse.setItems(body.getStatementList());
    }
}
