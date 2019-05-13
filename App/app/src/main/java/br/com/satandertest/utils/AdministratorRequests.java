package br.com.satandertest.utils;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Iago.
 * Usado para fazer get, put, post, e delete.
 * Para get, put, e delete:
 * Passa url que é o endereço que será feita a requisição
 * E a Classe RespostaRequisicao para lidar com a resposta da requisição
 * Para post:
 * Passa o json que será enviado.
 * E os mesmos dois parâmetros acima
 */
public class AdministratorRequests {
    private static final String TAG = "adm_rest";

    public static final String FORMAT_JSON = "?format=json";
    public static final MediaType JSONMediaType = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType TEXT_PLAIN_MediaType = MediaType.parse("text/plain; charset=utf-8");


    public static void get(final String url, RespostaRequisicao resposta) {
        Log.d(TAG, "GET web: " + url);
        resposta.setUrl(url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        new OkHttpClient().newCall(request).enqueue(resposta);
    }

    public static void postFormBody(final RequestBody form, final String url, RespostaRequisicao resposta) {
        Log.d(TAG, "POST web: " + url + ", form: " + form);

        resposta.setUrl(url);
        OkHttpClient client = new OkHttpClient();

        client.setConnectTimeout(30, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(30, TimeUnit.SECONDS);

        Request request = new Request.Builder()
                .post(form)
                .url(url)
                .build();
        client.newCall(request).enqueue(resposta);
    }

    /**
     * Classe para lidar com a resposta da requisicao.
     * É chamado onResponse quando houver resposta do servidor:
     * E chamado as seguintes funções com body como parâmetro:
     * Se resposta sucessful é chamado respostaSucesso
     * Se resposta HTTP_OK chama respostaOK
     * Se resposta HTTP_NOT_FOUND chama respostaNotFound
     * Se não conseguir extrair o body(), ou Foi resposta onFailure chama-se a função falhou()
     * Se falhou() então refazer a requisição
     */
    public static class RespostaRequisicao implements Callback {
        private String url;

        public void setUrl(String _url) {
            url = _url;
        }

        @Override
        public void onResponse(Response response) throws IOException {
            Log.d(TAG, "Resposta da requisicao: " + response.code() + ", na url: " + url);
            try {
                String body = response.body().string();
                if (response.isSuccessful()) {
                    respostaSucesso(body);
                } else {
                    System.out.println("FALHA BODY: " + body);
                }
                switch (response.code()) {
                    case HttpURLConnection.HTTP_OK:
                        respostaOK(body);
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                        respostaNotFound(body);
                        break;

                }
            } catch (SocketTimeoutException e) {
                falhou(e);
            } catch (OutOfMemoryError e) {
                falhou(new Exception());
            }
            fimDaThread();
        }

        public void respostaNotFound(String body) {

        }

        public void respostaSucesso(String body) {

        }

        public void respostaOK(String body) {

        }

        @Override
        public void onFailure(Request request, IOException e) {
            falhou(e);
            fimDaThread();
        }

        public void fimDaThread() {

        }

        public void falhou(Exception e) {
            if (url == null) return;
            Log.w(TAG, "FALHOU web: " + url + " , erro: " + e.getMessage());
            /*Utilities.runOnUiThreadDelayed(new Runnable() {
                @Override
                public void run() {
                    get(url, RespostaRequisicao.this);
                }
            }, 5000);*/
        }
    }


}
