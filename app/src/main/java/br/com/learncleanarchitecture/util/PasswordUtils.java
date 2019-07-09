//package br.com.learncleanarchitecture.util;
//
//import android.content.Context;
//import br.com.learncleanarchitecture.R;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Created by BRQ.
// *
// * Class used to perform Passwords treatments.
// */
//public class PasswordUtils {
//
//    /**
//     * Enum containing password states.
//     */
//    public enum Password{
//        SUCCESS, REPEATED, SEQUENCE, SEQUENCE_INVERTED, CPF, CPF_INVERTED
//    }
//
//    /**
//     * Method used to get a password error, if it exists.
//     * @param context Application Context.
//     * @param password String to be validated.
//     * @param cpf User's CPF.
//     * @return String containing the password error, if there's no error, an empty string is returned.
//     */
//    public static String getPasswordError(Context context, String password, String cpf){
//        switch (isValidPassword(password, cpf)){
//            case REPEATED:
//                return context.getString(R.string.text_input_layout_warning_password_repeated);
//            case SEQUENCE:
//                return context.getString(R.string.text_input_layout_warning_password_sequence);
//            case SEQUENCE_INVERTED:
//                return context.getString(R.string.text_input_layout_warning_password_sequence_inverted);
//            case CPF:
//                return context.getString(R.string.text_input_layout_warning_password_cpf);
//            case CPF_INVERTED:
//                return  context.getString(R.string.text_input_layout_warning_password_cpf_inverted);
//            default: return "";
//        }
//    }
//
//    /**
//     * Method used to validate a password.
//     * @param password String to be validated.
//     * @param cpf User's CPF.
//     * @return Enum Password state value.
//     */
//    public static Password isValidPassword(String password, String cpf) {
//        String reversedCpf = new StringBuilder(cpf).reverse().toString();
//
//        if(isRepeatedSequence(password)) {
//            return Password.REPEATED;
//        }
//
//        if (isConsecutiveSequence(password)) {
//            return Password.SEQUENCE;
//        }
//
//        if (isInvertedConsecutiveSequence(password)) {
//            return Password.SEQUENCE_INVERTED;
//        }
//
//
//        if (isCpfOnPassword(password, cpf)) {
//            return Password.CPF;
//        }
//
//        if (isCpfOnPassword(password, reversedCpf)) {
//            return Password.CPF_INVERTED;
//        }
//
//        return Password.SUCCESS;
//    }
//
//    /**
//     * Method used to check if the password has repeated characters.
//     * @param password String to be validated.
//     * @return TRUE if it has repeated characters, FALSE otherwise.
//     */
//    private static boolean isRepeatedSequence(String password)  {
//        String patternRepeatedValue = "(?i)(?:([0-9])\\1{2,})";
//        Pattern pattern = Pattern.compile(patternRepeatedValue);
//        Matcher matcher = pattern.matcher(password);
//
//        return matcher.find();
//    }
//
//    /**
//     * Method used to check if the password has sequential characters.
//     * @param password String to be validated.
//     * @return TRUE if it has sequential characters, FALSE otherwise.
//     */
//    private static boolean isConsecutiveSequence(String password) {
//        char pwdCharArray[] = password.toCharArray();
//        int asciiCode;
//        int previousAsciiCode = 0;
//        int sequenceCount = 0;
//
//        boolean isConsecutiveSequence = false;
//        for (char aPwdCharArray : pwdCharArray) {
//            asciiCode = aPwdCharArray;
//            if ((previousAsciiCode + 1) == asciiCode) {
//                sequenceCount++;
//                if (sequenceCount >= 2) {
//                    isConsecutiveSequence = true;
//                    break;
//                }
//            } else {
//                sequenceCount = 0;
//            }
//            previousAsciiCode = asciiCode;
//        }
//        return isConsecutiveSequence;
//    }
//
//    /**
//     * Method used to check if the password has sequential inverted characters.
//     * @param password String to be validated.
//     * @return TRUE if it has sequential inverted characters, FALSE otherwise.
//     */
//    private static boolean isInvertedConsecutiveSequence(String password) {
//        char pwdCharArray[] = password.toCharArray();
//        int asciiCode;
//        int previousAsciiCode = 0;
//        int sequenceCount = 0;
//
//        boolean isInvertedConsecutiveSequence = false;
//        for (char aPwdCharArray : pwdCharArray) {
//            asciiCode = aPwdCharArray;
//            if ((previousAsciiCode - 1) == asciiCode) {
//                sequenceCount++;
//                if (sequenceCount >= 2) {
//                    isInvertedConsecutiveSequence = true;
//                    break;
//                }
//            } else {
//                sequenceCount = 0;
//            }
//            previousAsciiCode = asciiCode;
//        }
//        return isInvertedConsecutiveSequence;
//    }
//
//    /**
//     * Method used to validate if the password contains part of the CPF.
//     * @param password String to be validated.
//     * @param cpf User's CPF.
//     * @return TRUE if it contains part of the CPF, FALSE otherwise.
//     */
//    private static boolean isCpfOnPassword(String password, String cpf) {
//        String cpfTemp;
//        boolean isCpfOnPass = false;
//
//        for(int i = 0; i < cpf.length() - 2; i++) {
//            cpfTemp = cpf.substring(i, i + 3);
//            if(password.contains(cpfTemp)) {
//                isCpfOnPass = true;
//                break;
//            }
//        }
//        return isCpfOnPass;
//    }
//}
