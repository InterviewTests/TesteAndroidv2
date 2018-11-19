package com.example.fabio.bankapp.classes;

import android.text.TextWatcher;
import android.widget.EditText;

import com.example.fabio.bankapp.LoginActivity;
import com.example.fabio.bankapp.R;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MascaraTest {

    @Test
    public void removerMascara() {
        String s = "316.878.008-12";
        Assert.assertEquals("31687800812", Mascara.RemoverMascara(s));
    }

    @Test
    public void inserirMascaraFormatoCPF() {
        String str = "31687800812";
        String mask = "###.###.###-##";
        String mascara = "";
        if (str.length()<= 14 && str.matches("[0-9]*")) {
            int i = 0;
            for (char m : mask.toCharArray()) {
                if (m != '#') {
                    mascara += m;
                    continue;
                }
                try {
                    mascara += str.charAt(i);
                } catch (Exception e) {
                    break;
                }
                i++;
            }
        }
        Assert.assertEquals("316.878.008-12", mascara);
    }
}