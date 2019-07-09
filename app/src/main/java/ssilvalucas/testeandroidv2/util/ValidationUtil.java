package ssilvalucas.testeandroidv2.util;

public final class ValidationUtil {

    public static boolean isValidCpf(String cpf){
        cpf.replaceAll("[\\.-]", "");
        return false;
    }
}
