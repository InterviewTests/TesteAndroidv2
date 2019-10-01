package com.riso.zup.bank.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.riso.zup.bank.R;

public class Utils extends AppCompatActivity {

    ProgressDialog mProgressDialog;

    public String retrieveStringCache(int path, int key){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(path), 0);
        String user = sharedPreferences.getString(getString(key), null);
        if(user != null)
            return user;
        else
            return null;
    }

    public void saveStringCache(int path, int key, String user){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(path), 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.pref_key_user), user);
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
