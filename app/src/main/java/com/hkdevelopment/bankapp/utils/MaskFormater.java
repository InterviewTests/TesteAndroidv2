package com.hkdevelopment.bankapp.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

public class MaskFormater implements MaskFormaterInt {
    @Override
    public String formateDate(String date) throws ParseException {
        SimpleDateFormat oldFormat =  new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormat =  new SimpleDateFormat("dd/MM/yyyy");

        return newFormat.format(oldFormat.parse(date));
    }

    @Override
    public String formateAgency(String agency) {
        return  new StringBuilder(agency).insert(agency.length()-1, "-").insert(2,".").toString();
    }

    @Override
    public String formatValue(Double value) {
        NumberFormat format=NumberFormat.getCurrencyInstance();
        format.setCurrency(Currency.getInstance("BRL"));
        return format.format(value);
    }
}
