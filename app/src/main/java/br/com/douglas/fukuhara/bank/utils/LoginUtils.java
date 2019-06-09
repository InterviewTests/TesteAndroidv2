package br.com.douglas.fukuhara.bank.utils;

import android.content.Intent;

import java.util.Locale;
import java.util.regex.Pattern;

import br.com.douglas.fukuhara.bank.network.vo.UserAccount;

public final class LoginUtils {
    // Constants used in CPF verification
    private static final int CPF_LENGTH_WITHOUT_DIGITS = 9;
    private static final int CPF_LENGTH_FULL_FORM = 11;
    private static final int CPF_FIRST_DIGIT_POSITION = 10;
    private static final int CPF_SECOND_DIGIT_POSITION = CPF_LENGTH_FULL_FORM;

    // Constant for Un/Serialize UserAccount object from Bundle
    private static final String BUNDLE_USER_ACCOUNT_OBJ = "bundle_user_account_obj";

    /*
        Password validation
     */
    public static boolean isValidPasswordPattern(String password) {
        // To be considered valid, the required pattern for the password is:
        // - at least one Capitol Letter : (?=.*[A-Z])
        // - at least one special char : (?=.*[+×÷=/_€£¥₩!@#$%^&*:;,?\|])
        // - at least one alphanumeric : (?=.*[0-9])
        String regex = "(?=.*[A-Z])(?=.*[+×÷=/_€£¥₩!@#$%^&*:;,?\\|])(?=.*[0-9])[0-9a-zA-Z+×÷=/_€£¥₩!@#$%^&*:;,?\\|]+";

        return Pattern.matches(regex, password);
    }

    /*
        Username validation
     */
    public static @UsernameValidation.Type int isValidUsernamePattern(String username) {
        // First, lets check if the informed username can be a CPF input
        if (isThisAPossibleCpfValue(username)) {
            return cpfValidation(username);
        }
        // If the input is not a CPF-like value, lets check if it fits in email pattern
        if (hasEmailPattern(username)) {
            return UsernameValidation.VALID_EMAIL;
        }
        // In case that it doesn't fit neither in CPF nor in Email pattern, return as
        // an generic error
        return UsernameValidation.INVALID_EMAIL_CPF;
    }

    private static boolean isThisAPossibleCpfValue(String username) {
        String regex = "^(?:[0-9]{11}|[0-9]{3}(?:\\.[0-9]{3}){2}\\-[0-9]{2})$";
        return Pattern.matches(regex, username);
    }

    private static @UsernameValidation.Type int cpfValidation(String username) {
        // In case that the given input has the format of a CPF number, lets
        // calculate the checksum to validate this number
        String regex = "[\\.-]";
        String plainNumber = username.replaceAll(regex, "");

        if (plainNumber.length() != CPF_LENGTH_FULL_FORM) {
            return UsernameValidation.INVALID_CPF;
        }

        String cpfWithoutDigits = plainNumber.substring(0, CPF_LENGTH_WITHOUT_DIGITS);

        String calculatedCpf = calculateCpf(cpfWithoutDigits, CPF_FIRST_DIGIT_POSITION);
        calculatedCpf = calculateCpf(calculatedCpf, CPF_SECOND_DIGIT_POSITION);

        if (plainNumber.equals(calculatedCpf)) {
            return UsernameValidation.VALID_CPF;
        }
        return UsernameValidation.INVALID_CPF;
    }

    private static String calculateCpf(String partialCpf, int digitToCalculate) {
        int sum = 0;
        int currentDigit, modResult, calculatedDigit;
        for (char digit :partialCpf.toCharArray()) {
            currentDigit = Integer.parseInt(String.valueOf(digit));
            sum = sum + currentDigit * digitToCalculate;
            digitToCalculate--;
        }
        modResult = sum % CPF_LENGTH_FULL_FORM;
        if (modResult > 1) {
            calculatedDigit = CPF_LENGTH_FULL_FORM - modResult;
        } else {
            calculatedDigit = 0;
        }

        return partialCpf + calculatedDigit;
    }

    private static boolean hasEmailPattern(String username) {
        String regex = "^[a-z](?:[0-9a-z\\.\\-_])*?@[0-9a-z][0-9a-z-_]*(?:\\.[0-9a-z-_]+)*(?:\\.[0-9a-z]+)+$";

        return Pattern.matches(regex, username);
    }

    public static String formatLoginErrorMsg(String message, int code) {
        return String.format(Locale.getDefault(), "%s (%d)", message, code);
    }

    public static void serializeUserAccountObj(UserAccount userAccount, Intent intent) {
        intent.putExtra(BUNDLE_USER_ACCOUNT_OBJ, userAccount);
    }

    public static UserAccount getUserAccountFromBundle(Intent intent) {
        if (intent.hasExtra(BUNDLE_USER_ACCOUNT_OBJ)) {
            return intent.getParcelableExtra(BUNDLE_USER_ACCOUNT_OBJ);
        }
        return null;
    }
}
