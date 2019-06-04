package com.project.personal.app_bank.utils;

public class CheckUser {

    private static boolean checkEmail(String s) {
        boolean email = s.matches("\\w+@\\w+\\.\\w{2,3}\\.\\w{2,3}");
        return email;
    }


    private static boolean checkCPF(String s) {
        boolean cpf = s.matches("\\d{11}");

        return cpf;
    }

    public static boolean checkUser(String s){
        if(checkEmail(s) || checkCPF(s) == true){
            return true;
        }else{
            return false;
        }
    }
}
