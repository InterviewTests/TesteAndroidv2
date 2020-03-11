package com.test.banktest.worker.preferenceWorker;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceWorker implements PreferenceWorkerInput {
    String pref_key = "bank_prefs";

    @Override
    public String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key,null);
    }

    public void saveValue(Context context, String key, String value) {
         getSharedPreferences(context)
                 .edit()
                 .putString(key,value)
                 .apply();
    }

    private SharedPreferences getSharedPreferences(Context context){
       return context.getSharedPreferences(pref_key, Context.MODE_PRIVATE);
    }
}