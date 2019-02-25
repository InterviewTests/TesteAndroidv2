package com.santander.app.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Convert {
    public static String UsDateToBrDate(String date){
        String retorno = "";

        try{
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(date);
            cal.setTime(d);
            retorno = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
        }
        catch (Exception ex){

        }

        return retorno;
    }
}
