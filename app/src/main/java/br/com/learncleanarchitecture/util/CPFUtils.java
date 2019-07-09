package br.com.learncleanarchitecture.util;


/**
 * Created by BRQ.
 *
 * Class used to perform CPF treatments.
 */
public class CPFUtils {

    /**
     * Validates whether a given CPF is valid or invalid.
     * @param CPF String to be validated.
     * @return TRUE if CPF is valid, FALSE is CPF is invalid.
     */
    public static boolean isValidCPF(String CPF) {
        CPF = CPF.replace(".", "").replace("-","");
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, weight;


        sm = 0;
        weight = 10;
        for (i=0; i<9; i++) {

            num = (CPF.charAt(i) - 48);
            sm = sm + (num * weight);
            weight = weight - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig10 = '0';
        else dig10 = (char)(r + 48);

        sm = 0;
        weight = 11;
        for(i=0; i<10; i++) {
            num = (CPF.charAt(i) - 48);
            sm = sm + (num * weight);
            weight = weight - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig11 = '0';
        else dig11 = (char)(r + 48);


        return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
    }
}
