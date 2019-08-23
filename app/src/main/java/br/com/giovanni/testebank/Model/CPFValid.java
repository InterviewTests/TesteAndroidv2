package br.com.giovanni.testebank.Model;

import java.util.ArrayList;
import java.util.Collections;

public class CPFValid {
    private String cpf;

    public CPFValid(String cpf) {
        this.cpf = cpf.replace(".", "").replace("-", "");
    }

    private static ArrayList<Integer> convertStringToArray(String cpf) {
        ArrayList<Integer> array = new ArrayList();
        for (int i = 0; i < cpf.length(); i++) {
            array.add(Character.getNumericValue(cpf.charAt(i)));
        }
        return array;
    }

    public boolean isValid() {
        return testCPF(this.cpf);
    }

    public static boolean testCPF(String cpf) {
        if (cpf.matches("/[0-9]{11,11}"))
            return false;
        ArrayList<Integer> cpfArray = convertStringToArray(cpf);
        if (cpfArray.size() != 11)
            return false;

        int firstDigit, secondDigit;
        firstDigit = cpfArray.get(cpfArray.size() - 2);
        secondDigit = cpfArray.get(cpfArray.size() - 1);
        Collections.reverse(cpfArray);
        return checkFirstDigit(cpfArray, firstDigit) && checkSecondDigit(cpfArray, secondDigit);
    }

    private static boolean checkFirstDigit(ArrayList<Integer> list, int digit) {
        int sum = 0;
        for (int i = 10; i > 1; i--) {
            sum += list.get(i) * i;
        }
        int result = (sum * 10) % 11;
        result = (result == 10) ? 0 : result;
        if (result == digit)
            return true;
        else
            return false;
    }

    private static boolean checkSecondDigit(ArrayList<Integer> list, int digit) {
        int sum = 0;
        for (int i = 11; i > 1; i--) {
            sum += list.get(i - 1) * i;
        }
        int result = (sum * 10) % 11;
        result = (result == 10) ? 0 : result;
        if (result == digit)
            return true;
        else
            return false;
    }

    public static boolean checkCPF(String cpf) {
        cpf = cpf.replace(".", "").replace("-", "");
        return testCPF(cpf);
    }

}