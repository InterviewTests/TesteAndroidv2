package com.bilulo.androidtest04.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean containsEmailCharacters(String s) {
        Pattern p = Pattern.compile("([a-zA-Z][0-9]*@*\\.*)+");
        Matcher m = p.matcher(s);
        return m.find();
    }
}
