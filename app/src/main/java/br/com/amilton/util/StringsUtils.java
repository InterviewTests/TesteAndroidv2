package br.com.amilton.util;

public class StringsUtils {

    /**
     *
     * @param input
     * @return
     */
    public static boolean checkPassword(String input) {
        String specialCharacters = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        char currentCharacter;
        boolean numberPresent = false;
        boolean upperCasePresent = false;
        boolean specialCharacterPresent = false;

        for (int i = 0; i < input.length(); i++) {
            currentCharacter = input.charAt(i);
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            } else if (Character.isUpperCase(currentCharacter)) {
                upperCasePresent = true;
            } else if (specialCharacters.contains(String.valueOf(currentCharacter))) {
                specialCharacterPresent = true;
            }
        }

        return numberPresent && upperCasePresent && specialCharacterPresent;
    }
}
