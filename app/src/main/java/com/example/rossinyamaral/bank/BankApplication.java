package com.example.rossinyamaral.bank;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.rossinyamaral.bank.model.UserAccountModel;

/**
 * Created by rossinyamaral on 08/12/18.
 */

public class BankApplication extends Application {
    public static final String TAG = BankApplication.class.getSimpleName();
    private static BankApplication mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private UserAccountModel currentUser;
    public BankApi bankApi;

    public BankApplication() {
        super();
        mInstance = this;
        bankApi = new BankApi();
    }

    public static BankApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue, null
                    //new LruBitmapCache()
            );
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public String getParam(String key, String defaultValue) {
        SharedPreferences sharedPref = getSharedPreferences("sharedParam", Context.MODE_PRIVATE);
        return sharedPref.getString(key, defaultValue);
    }

    public void setParam(String key, String value) {
        SharedPreferences sharedPref = getSharedPreferences("sharedParam", Context.MODE_PRIVATE);
        sharedPref.edit().putString(key, value).commit();
    }

    public void setLastLogin(String username, String password) {
        setParam("lastLogin", username);
        setParam("lastPassword", password);
    }

    public String getLastLogin() {
        return getParam("lastLogin", "");
    }

    public String getLastPassword() {
        return getParam("lastPassword", "");
    }
}
