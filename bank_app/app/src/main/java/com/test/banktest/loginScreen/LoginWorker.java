package com.test.banktest.loginScreen;


import com.test.banktest.util.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface LoginWorkerInput {
    public boolean validateUser(String email);
    public boolean validatePassword(String password);
}

public class LoginWorker implements LoginWorkerInput {

    public boolean validateUser(String user){

        if(TextUtils.isEmpty(user)) return false;

        boolean isEmailIdValid = isEmailValid(user);
        if(isEmailIdValid){
            return true;
        }
        return isValidCPF(user);
    }

    private boolean isEmailValid(String user){
        if (user != null && user.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(user);
            return matcher.matches();
        }
        return false;
    }
    public boolean validatePassword(String password){
        if (TextUtils.isEmpty(password)) return false;

        String pattern = "(?=.*[a-z0-9])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,}";
        return password.matches(pattern);
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean isValidCPF(String cpf) {
        int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        if ((cpf==null) || (cpf.length()!=11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

}
