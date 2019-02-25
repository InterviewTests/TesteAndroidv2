package com.santander.app.util;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import java.util.regex.Pattern;

public class Validacao {
    public static boolean isUserOK(String user) {
        try {
            if (!TextUtils.isEmpty(user)) {
                if (Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                    return true;
                } else {
                    if(isCPFOk(user)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            Log.e("Validacao", ex.getLocalizedMessage());
            return false;
        }
    }

    public static boolean isPasswordOK(String password){
        try{
            if(password.length() < 3){
                return false;
            }
            else{
                //verifica se tem pelo menos uma maiuscula
                if(!password.equals(password.toLowerCase())){
                    //tem ao menos uma maiuscula

                    //verifica se tem um caracter alfanumerico
                    if(!password.matches("[A-Za-z0-9 ]*")){
                        //tem ao menos um caracter alfanumérico
                        if(!password.matches("[^a-z0-9 ]")){
                            //tem ao menos um caracter especial
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
        }
        catch (Exception ex){
            Log.e("Validacao", ex.getLocalizedMessage());
            return false;
        }
    }

    public static boolean isCPFOk(String cpf) {
        Pattern PADRAO_COM_PONTOS_TRACOS = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
        Pattern PADRAO_SOMENTE_NUMEROS = Pattern.compile("(?=^((?!((([0]{11})|([1]{11})|([2]{11})|([3]{11})|([4]{11})|([5]{11})|([6]{11})|([7]{11})|([8]{11})|([9]{11})))).)*$)([0-9]{11})");


        if (cpf != null && PADRAO_COM_PONTOS_TRACOS.matcher(cpf).matches()) {
            cpf = cpf.replaceAll("-|\\.", "");
            if (cpf != null && PADRAO_SOMENTE_NUMEROS.matcher(cpf).matches()) {

                int[] numeros = new int[11];
                for (int i = 0; i < 11; i++) numeros[i] = Character.getNumericValue(cpf.charAt(i));

                int i;
                int soma = 0;
                int fator = 100;

                for (i = 0; i < 9; i++) {
                    soma += numeros[i] * fator;
                    fator -= 10;
                }

                int sobra = soma % 11;

                sobra = sobra == 10 ? 0 : sobra;

                if (sobra == numeros[9]) {
                    soma = 0;
                    fator = 110;

                    for (i = 0; i < 10; i++) {
                        soma += numeros[i] * fator;
                        fator -= 10;
                    }

                    sobra = soma % 11;
                    sobra = sobra == 10 ? 0 : sobra;
                    return sobra == numeros[10];
                }
            }
        }
        return false;

    }
}
