package com.bankapp.api;

import com.bankapp.ErrorResponse;

public interface RequestListener<T> {

    void onSuccess(T response);
    void onFailure(T response);
    void onFailure(ErrorResponse error);

}
