package com.bank.testeandroidv2.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class DialogUtil {
    private static ProgressDialog dialog;
    private static Context context;

    public DialogUtil(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    public static void showProgress(String msg) {
        dialog.setMessage(msg);
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void hideProgress() {
        dialog.dismiss();
    }

    public static void showErrorMessage(String msg) {
        CharSequence text = msg;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
