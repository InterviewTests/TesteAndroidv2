package com.accenture.android.app.testeandroid.helpers;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Denis Magno on 10/07/2020.
 * denis_magno16@hotmail.com
 */
public class FormatHelper {
    public static String formatarReal(Double valor) {
        String formated = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);

        // Verifica se número formatado está entre parenteses
        // Se sim, número é negativo, então formata o número para retirar os parenteses e
        // colocar o '-' na frente.
        if (formated.contains("(")) {
            formated = formated.replaceAll("[()]", "");

            formated = "- " + formated;
        }

        return formated;
    }

    public static String formatarAgenciaBanco(String agencia) {
        if (agencia.length() < 9) {
            int numerosFaltantes = 9 - agencia.length();

            StringBuilder agenciaBuilder = new StringBuilder(agencia);
            for (int i = 0; i < numerosFaltantes; i++) {
                agenciaBuilder.insert(0, "0");
            }
            agencia = agenciaBuilder.toString();
        }
        return agencia.substring(0, 2) + "." + agencia.substring(2, agencia.length() - 1) + "-" + agencia.substring(agencia.length() - 1);
    }

    public static String formatarData(Calendar data) {
        Integer dia = data.get(Calendar.DAY_OF_MONTH);
        String mes = new SimpleDateFormat("MM", Locale.getDefault()).format(data.getTime());
        Integer ano = data.get(Calendar.YEAR);

        return String.format(Locale.getDefault(), "%d/%s/%d", dia, mes, ano);
    }
}
