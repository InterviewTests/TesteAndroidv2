package com.test.banktest.worker.preferenceWorker;

import android.content.Context;

public interface PreferenceWorkerInput {
    public String getString(Context context, String key);
    public void saveValue(Context context, String key, String value);
}
