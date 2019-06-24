package com.bilulo.androidtest04.common;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.bilulo.androidtest04.R;

public abstract class BaseActivity extends AppCompatActivity {
    private AlertDialog progressDialog;

    protected void showProgressDialog() {
        progressDialog = progressDialog == null ? new AlertDialog.Builder(this).
                setView(R.layout.dialog_progress).create() : progressDialog;
        if (!progressDialog.isShowing()) {
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.show();
        }
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    protected void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
    }

}
