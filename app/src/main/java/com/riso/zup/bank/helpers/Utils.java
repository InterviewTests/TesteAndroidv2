package com.riso.zup.bank.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

public class Utils {

    ProgressDialog mProgressDialog;

    public String retrieveStringCache(Context context, String path, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(path, 0);
        String user = sharedPreferences.getString(key, null);
        if(user != null)
            return user;
        else
            return null;
    }

    public void saveStringCache(Context context, String path, String key, String user){
        SharedPreferences sharedPreferences = context.getSharedPreferences(path, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(path, user);
        editor.commit();
    }

    public void showToast(Context contex, String message ){
        Toast.makeText(contex,
                message,
                Toast.LENGTH_LONG)
                .show();
    }

    public void showProgressDialog(Context context, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCancelable(false);
            mProgressDialog.isIndeterminate();
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showErrorDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
