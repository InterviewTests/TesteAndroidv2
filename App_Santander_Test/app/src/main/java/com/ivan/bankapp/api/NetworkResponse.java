package com.ivan.bankapp.api;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;

class NetworkResponse<ResponseType> implements Callback<ResponseType> {

    private WeakReference<NetworkResponseListener<ResponseType>> listener;

    NetworkResponse(NetworkResponseListener<ResponseType> listener) {
        this.listener = new WeakReference<>(listener);
    }
    @Override
    public void onResponse(Call<ResponseType> call, retrofit2.Response<ResponseType> response) {

        if (listener != null && listener.get() != null) {
            listener.get().onResponseReceived(response.body());
        }
    }

    @Override
    public void onFailure(@NonNull Call<ResponseType> call, @NonNull Throwable t) {
        if (listener != null && listener.get() != null) {
            listener.get().onError();
        }
    }
}