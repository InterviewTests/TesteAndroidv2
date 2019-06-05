package com.example.gabriela.testeandroidv2.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDataBr(String data) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date testDate = null;
        try {
            testDate = sdf.parse(data);
        } catch (Exception ex) {
            Log.e("DATEUTIL", String.valueOf(ex));
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String newFormat = formatter.format(testDate);
        return newFormat;
    }
}
