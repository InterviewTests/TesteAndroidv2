package com.riso.zup.bank.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AlertDialog;

import com.riso.zup.bank.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
        editor.putString(key, user);
        editor.commit();
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

    public static String formatNumberAgency(String agency){
        agency = (agency.substring(0, agency.length()-1));
        agency = agency+"-"+agency.substring(agency.length()-1, agency.length());

        return agency;
    }

    public static String formatValue(Context context, Double value){

        NumberFormat format = NumberFormat.getCurrencyInstance();
        String valueString = format.format(value);
        valueString = valueString.replaceAll("\\$", "");
        if(valueString.contains("-"))
            valueString = valueString.replaceAll("-","");

        valueString = context.getResources().getString(R.string.currency)+" "+valueString;

        return valueString;
    }

    public static String formatDate(String value){

        String data = null;

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
            data = new SimpleDateFormat("dd/MM/yyyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;

    }

}
