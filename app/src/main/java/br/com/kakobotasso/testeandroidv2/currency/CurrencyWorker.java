package br.com.kakobotasso.testeandroidv2.currency;

import br.com.kakobotasso.testeandroidv2.util.RetrofitConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface CurrencyWorkerInput{
    void getCurrencyInfo(int userId, Callback<CurrencyModel> callback);
}

interface CurrencyService {
    @GET("/api/statements/{userId}")
    Call<CurrencyModel> getCurrencyInfo(@Path("userId") int userId);
}

public class CurrencyWorker implements CurrencyWorkerInput {
    @Override
    public void getCurrencyInfo(int userId, Callback<CurrencyModel> callback) {
        CurrencyService service =
                (CurrencyService) RetrofitConfiguration.getService(CurrencyService.class);
        Call<CurrencyModel> getCurrencyInfo = service.getCurrencyInfo(userId);
        getCurrencyInfo.enqueue(callback);
    }
}
