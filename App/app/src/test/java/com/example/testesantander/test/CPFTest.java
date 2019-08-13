package com.example.testesantander.test;

import org.junit.Test;

import Models.CPF;

import static org.junit.Assert.*;

public class CPFTest {
    @Test
    public void testa_cpf_valido(){
        CPF cpf = new CPF("244.440.690-74");
        assertEquals(true, cpf.ehValido());

        cpf = new CPF("24444069074");
        assertEquals(true, cpf.ehValido());
    }

    @Test
    public void testa_cpf_vazio(){
        CPF cpf = new CPF("");
        assertEquals(false, cpf.ehValido());
    }

    @Test
    public void testa_caracteres(){
        // So aceita numeros
        CPF cpf = new CPF("244.440.690-7A");
        assertEquals(false, cpf.ehValido());

        // Menos de 11 caracteres
        cpf = new CPF("24444069");
        assertEquals(false, cpf.ehValido());

        // Mais de 11 caracteres
        cpf = new CPF("244.440.690-742434");
        assertEquals(false, cpf.ehValido());
    }

    @Test
    public void testa_cpf_invalido(){
        CPF cpf = new CPF("244.440.690-73");
        assertEquals(false, cpf.ehValido());

        cpf = new CPF("24444069073");
        assertEquals(false, cpf.ehValido());

        // Mais de 11 caracteres
        cpf = new CPF("111.111.111-11");
        assertEquals(false, cpf.ehValido());
    }
}