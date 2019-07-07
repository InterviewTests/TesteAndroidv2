package ssilvalucas.testeandroidv2.utils;

public final class ValidationUtils {

    public static boolean isValidCpf(String cpf){
        cpf.replaceAll("[\\.-]", "");
        return false;
    }
}
