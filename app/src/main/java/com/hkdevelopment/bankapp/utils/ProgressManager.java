package com.hkdevelopment.bankapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressManager implements ProgressManagerInt {
    private ProgressDialog pgDialog;
    private Context context;

    public ProgressManager(Context context) {
        this.context = context;
        this.pgDialog = new ProgressDialog(context);
    }

    @Override
    public void showDialog(String msg) {
        pgDialog.setIndeterminate(true);
        pgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pgDialog.setTitle("Loading");
        pgDialog.setMessage(msg);
        pgDialog.show();
    }

    @Override
    public void purgeDialog() {
        if (pgDialog.isShowing())
            pgDialog.dismiss();
    }
}
