package com.bilulo.androidtest04.ui.list.router;

import android.content.Intent;
import com.bilulo.androidtest04.ui.list.view.ListActivity;
import com.bilulo.androidtest04.ui.login.view.LoginActivity;

import java.lang.ref.WeakReference;

public class ListRouter {
    public WeakReference<ListActivity> activity;

    public void startLoginActivity() {
        Intent intent = new Intent(activity.get(), LoginActivity.class);
        activity.get().startActivity(intent);
        activity.get().finish();
    }
}
