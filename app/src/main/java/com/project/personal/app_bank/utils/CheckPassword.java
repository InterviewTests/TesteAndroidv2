package com.project.personal.app_bank.utils;

import android.util.Log;

import com.project.personal.app_bank.models.LoginRequest;

public class CheckPassword {


    public static boolean validPassword(String s){

        boolean checkUpper = false;//s.matches("\\p{Upper}"); // \p{Upper} == maiúsculas, p{Alnum} == alfanumérico, p{Punct}== caracter especial
        boolean checkAlpha = false;
        boolean checkEspecial = false;

        for(int i = 0; i<s.length(); i++){
            String c = String.valueOf(s.charAt(i));
            if(c.matches("\\p{Upper}") == true){
                checkUpper = true;
            }else{
                if(c.matches("\\p{Alnum}") == true){
                    checkAlpha = true;
                }else{
                    if(c.matches("\\p{Punct}") == true){
                        checkEspecial = true;
                    }
                }
            }
        }

        if(checkAlpha ==true && checkEspecial==true && checkUpper == true){
            return true;
        }else{
            return false;
        }
    }
}


