package com.hkdevelopment.bankapp.utils;

import java.text.ParseException;

public interface MaskFormaterInt {

    String formateDate(String date) throws ParseException;
    String formateAgency(String agency);
    String formatValue(Double value);
}
