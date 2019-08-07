package com.example.testesantander.test;

import org.junit.Test;

import Domain.Senha;

import static org.junit.Assert.*;

public class SenhaTest {

    @Test
    public void testa_senha_valida(){
        Senha senha = new Senha("Senha_1");
        assertEquals(true, senha.ehValida());

        senha = new Senha("S3nh@-1.7#");
        assertEquals(true, senha.ehValida());

        senha = new Senha("SdsiçDECOxmaxanmx_@#4sd1342-91");
        assertEquals(true, senha.ehValida());
    }

    @Test
    public void testa_senha_vazia(){
        Senha senha = new Senha("");
        assertEquals(false, senha.ehValida());
    }

    @Test
    public void testa_senha_sem_maiuscula(){
        Senha senha = new Senha("senha");
        assertEquals(false, senha.ehValida());

        senha = new Senha("senha11");
        assertEquals(false, senha.ehValida());

        senha = new Senha("senha_");
        assertEquals(false, senha.ehValida());

        senha = new Senha("senha_99");
        assertEquals(false, senha.ehValida());
    }

    @Test
    public void testa_senha_sem_numero(){
        Senha senha = new Senha("Senha");
        assertEquals(false, senha.ehValida());

        senha = new Senha("Senha#FQDAS");
        assertEquals(false, senha.ehValida());
    }

    @Test
    public void testa_senha_sem_caracter_especial(){
        Senha senha = new Senha("senha123");
        assertEquals(false, senha.ehValida());

        senha = new Senha("Senha123");
        assertEquals(false, senha.ehValida());
    }
}