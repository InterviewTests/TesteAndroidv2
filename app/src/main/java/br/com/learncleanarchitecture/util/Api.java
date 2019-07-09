package br.com.learncleanarchitecture.util;


import br.com.learncleanarchitecture.login.data.api.Error;
import org.jetbrains.annotations.NotNull;

public class Api<T> implements Response<T>, InternetError {

    private Data<T> data;

    public Api() {

    }

    public Api(T data) {
        this.data = new Data<>(data);
    }

    public boolean isValid() {
        return data != null;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }

    public Data<T> getData() {
        return data;
    }

    @Override
    public void onSuccess(T number) {
        this.data = new Data<>(number);
    }


    @Override
    public void onInternetError(String error) {

    }

    @Override
    public void onError(@NotNull Error response) {

    }

    @Override
    public void onThrowable(@NotNull Throwable response) {

    }
}

