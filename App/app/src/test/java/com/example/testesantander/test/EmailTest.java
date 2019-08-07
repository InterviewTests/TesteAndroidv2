package com.example.testesantander.test;

import org.junit.Test;

import Domain.Email;

import static org.junit.Assert.*;

public class EmailTest {

    @Test
    public void testa_email_valido(){
        Email email = new Email("carlos@mail.com");
        assertEquals(true, email.ehValido());

        email = new Email("carlos.albuquerque@mail.com");
        assertEquals(true, email.ehValido());

        email = new Email("carlos_silva@mail.com");
        assertEquals(true, email.ehValido());
    }

    @Test
    public void testa_email_vazio(){
        Email email = new Email("");
        assertEquals(false, email.ehValido());
    }

    @Test
    public void teste_email_invalido(){
        Email email = new Email("carlos");
        assertEquals(false, email.ehValido());

        email = new Email("beto.silva");
        assertEquals(false, email.ehValido());

        email = new Email("Carlos@");
        assertEquals(false, email.ehValido());

        email = new Email("carlos@.com");
        assertEquals(false, email.ehValido());

        email = new Email("carlos@mail.net");
        assertEquals(false, email.ehValido());
    }
}