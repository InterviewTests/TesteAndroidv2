package br.com.fernandodutra.testeandroidv2.utils;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 17/06/2019
 * Time: 11:25
 * TesteAndroidv2
 */
public class ToolBox {

    public static String removeAccents(String caracter) {
        caracter = Normalizer.normalize(caracter, Normalizer.Form.NFD);
        caracter = caracter.replaceAll("[^\\p{ASCII}]", "").
                            replace(".", "").
                            replace("-", "");
        return caracter;
    }

    public static String converteDateToStr(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        String strData = sdf.format(data);
        return strData;
    }

    public static Date converteStrToDate(String strData) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        Date data = null;
        try {
            data = sdf.parse(strData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static boolean validEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static boolean validCPF(String cpf) {
        // remove accents for CPF
        cpf = ToolBox.removeAccents(cpf);
        // it is considered error CPF's formed by a sequence of equal numbers
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protects the code for any conversion errors of type (int)
        try {
            // Calculation of the 1st. Verifying digit
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                //converts the i-th character of cpf into a number:
                // for example, transforms the character '0' into integer 0
                // (48 is the position of '0' in the ASCII table)
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48); // converts its numeric character

            // Calculation of the 2nd. Verifying digit
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Check if the calculated digits match the entered digits.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean validPassword(String password) {

        boolean findNumber = false;
        boolean findUpperCase = false;
        boolean findLowerCase = false;
        boolean findSpecialCaracter = false;

        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                findNumber = true;
            } else if (c >= 'A' && c <= 'Z') {
                findUpperCase = true;
            } else if (c >= 'a' && c <= 'z') {
                findLowerCase = true;
            } else {
                findSpecialCaracter = true;
            }
        }
        return findNumber && findUpperCase && findLowerCase && findSpecialCaracter;
    }


}
