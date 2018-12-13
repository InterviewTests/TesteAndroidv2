package com.hkdevelopment.bankapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogManager implements DialogManagerInt {

    private  Context context;
    private ProgressDialog pgDialog;

    public DialogManager(Context context){
        this.context=context;
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
