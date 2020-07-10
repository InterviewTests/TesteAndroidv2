package com.accenture.android.app.testeandroid.data.http.providers.generics;

import com.accenture.android.app.testeandroid.data.http.RetrofitConfig;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public abstract class BaseProvider {
    protected final String TAG = "CustomLog - " + this.getClass().getSimpleName();

    protected final RetrofitConfig retrofit;

    public BaseProvider(String baseUrl) {
        this.retrofit = new RetrofitConfig(baseUrl);
    }

    public abstract void finish();
}
