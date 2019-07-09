package ssilvalucas.testeandroidv2.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ssilvalucas.testeandroidv2.data.model.StatementsResponse;

public interface StatementsService {

    @GET("statements/{userId}")
    Call<StatementsResponse> getStatements(@Path(value = "userId") long userId);
}
