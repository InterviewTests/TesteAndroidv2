package com.accenture.android.app.testeandroid.data.http.responses.generics;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class ResponseBase<T> {
    @SerializedName("statementList")
    private T data;
    private Object error;

    public T getData() {
        return this.data;
    }

    public Object getError() {
        return this.error;
    }
}
