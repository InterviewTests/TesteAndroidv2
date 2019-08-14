package br.com.giovanni.testebank;

import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import br.com.giovanni.testebank.Presenter.UserModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebClient {

    public TextView textViewJson;
    private UserModel userModel;

//    public UserModel getUserModel() {
//        return userModel;
//    }

    public void textViewJsonWebClient (TextView textViewJsonClient){
        this.textViewJson = textViewJsonClient;
    }

    public UserModel userValidate (String setUser, String setPassword){
        OkHttpClient client = new OkHttpClient();
        String url = "https://bank-app-test.herokuapp.com/api/login";
        Request request = new Request.Builder()
        //              ;
        .url(url)
        .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String json = response.body().string();
                userModel = new UserModel();
                userModel.getUsuario();
            }
        });

        return new UserModel();

    }







//    public String post(String json) throws IOException {
//
//

//        OkHttpClient client = new OkHttpClient();
//        String url = "https://bank-app-test.herokuapp.com/api/login";
//        Request request = new Request.Builder()
//        //              ;
//        .url(url)
//        .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                final String json = response.body().string();
//                userModel = new UserModel();
//                userModel.getUsuario();
//            }
//        });





//        // Criando Requisição
//        builder.url(url);
//        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(mediaType, json);
//        builder.post(body);
//        Request request = builder.build();
//        Response response = client.newCall(request).execute();
//
//        String jsonDeResposta = response.body().string();
//
//        return jsonDeResposta;

//    }

}