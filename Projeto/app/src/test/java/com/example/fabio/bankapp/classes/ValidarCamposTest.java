package com.example.fabio.bankapp.classes;

import android.util.Patterns;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ValidarCamposTest  {


    @Test
    public void validarCampoVazioSeFoiPeenchido() {
        String strText = "A";
        if (!strText.equals("")) {
            Assert.assertTrue(true);
        }else {
            Assert.fail("Preencha o campo");
        }
    }

    @Test
    public void validarCPFInformadoCorretamente() {
        String CPF = "316.878.008-12";
        CPF = Mascara.RemoverMascara(CPF);
        if (!CPF.equals("00000000000") & !CPF.equals("11111111111") & !CPF.equals("22222222222") & !CPF.equals("33333333333")
                & !CPF.equals("44444444444") & !CPF.equals("55555555555")
                & !CPF.equals("66666666666") & !CPF.equals("77777777777")
                & !CPF.equals("88888888888") & !CPF.equals("99999999999")) {

            char dig10, dig11;
            int sm, i, r, num, peso;
            try {
                sm = 0;
                peso = 10;
                for (i = 0; i < 9; i++) {
                    num = (int) (CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig10 = '0';
                else
                    dig10 = (char) (r + 48);
                sm = 0;
                peso = 11;
                for (i = 0; i < 10; i++) {
                    num = (int) (CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig11 = '0';
                else
                    dig11 = (char) (r + 48);
                if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                    Assert.assertTrue(true);

            } catch (Exception erro) {
                Assert.fail("CPF inválido");
            }
        }else{
            Assert.fail("CPF inválido");
        }
    }

    @Test
    public void validarEmail() {
        //Não conseguir testar pois usa Patterns
    }

    @Test
    public void validarPasswordPrenchidoCorretamente() {
        String password = "A@1";
        Pattern pe = Pattern.compile("[^a-zA-Z0-9]");
        Pattern pm = Pattern.compile("[A-Z]");
        Pattern pn = Pattern.compile("[0-9]");
        boolean temespecial = pe.matcher(password).find();
        boolean temmaiuscula = pm.matcher(password).find();;
        boolean temnumero = pn.matcher(password).find();;

        if (temespecial & temmaiuscula & temnumero) {
            Assert.assertTrue(true);
        }else {
            Assert.fail("Password inválido");
        }
    }
}