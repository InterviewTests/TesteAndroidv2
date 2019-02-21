package com.avanade.testesantander2;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.util.Log;

import com.avanade.testesantander2.loginScreen.LoginActivity;

public class MainActivity extends Activity {

    public static final String TAG = "MAIN ACTIVITY";
    public static final int REQUEST_CODE_PERMISSION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        solicitaPermissao(this);
    }

    public void solicitaPermissao(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permissão de Internet" + " ----------- NOT GRANTED ");
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.INTERNET)) {
                Log.i(TAG, "Permissão de Internet" + " ----------- HAS BEEN RATIONALE REQUESTED");
                new AlertDialog.Builder(activity.getApplicationContext())
                        .setMessage("Permita acesso a Internet para usar o aplicativo!")
                        .setPositiveButton("Allow", (dialog, which) -> ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_CODE_PERMISSION))
                        .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                        .create()
                        .show();
            } else {
                Log.i(TAG, "Permissão de Internet" + "----------- HAS BEEN REQUESTED");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, REQUEST_CODE_PERMISSION);
            }
        } else {
            Log.i(TAG, "Permissão de Internet" + " ----------- OK - GRANTED ");

            startActivity(new Intent(this, LoginActivity.class));
            finish();
            //startActivity(new Intent(this, ContatoActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                Log.i(TAG, "Request Result: Permission Internet");
                solicitaPermissao(getParent());
                break;

            default:
                Log.i(TAG, "Request Code != ALL");
        }
    }
}

