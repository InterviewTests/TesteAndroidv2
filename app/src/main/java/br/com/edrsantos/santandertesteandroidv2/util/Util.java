package br.com.edrsantos.santandertesteandroidv2.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String convertDateToString(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = null;
        try {
            Date parse = dateFormat.parse(date);
            formattedDate = targetFormat.format(parse);
        } catch (Exception e) {
//            e.printStackTrace();
            Log.d("convertDateToString", e.getMessage());
        }

        return formattedDate;
    }
}
