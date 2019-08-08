package com.example.testesantander.test;

import org.junit.Test;

import Models.CPF;
import Models.Email;
import Models.Senha;
import Models.Usuario;
import Padrões.ValoresPadrao;

import static org.junit.Assert.*;

public class UsuarioTest {
    @Test
    public void testa_usuario_valido(){
        Email email = new Email("carlos@mail.com");
        Senha senha = new Senha("Senha_#8");

        Usuario usuario = new Usuario(email, senha);
        assertEquals(true, usuario.ehValido());

        CPF cpf = new CPF("244.440.690-74");
        usuario = new Usuario(cpf, senha);
        assertEquals(true, usuario.ehValido());
    }

    @Test
    public void testa_usuario_invalido(){
        Email email = new Email("jose@net");
        Senha senha = new Senha("Senha_#8");

        Usuario usuario = new Usuario(email, senha);
        assertEquals(false, usuario.ehValido());

        CPF cpf = new CPF("244.440.690-71");
        usuario = new Usuario(cpf, senha);
        assertEquals(false, usuario.ehValido());

        email = new Email("carlos@mail.com");
        senha = new Senha("senha");
        usuario = new Usuario(email, senha);
        assertEquals(false, usuario.ehValido());
    }

    @Test
    public void testa_identificacao_login(){
        Email email = new Email("carlos@mail.com");
        assertEquals(ValoresPadrao.EMAIL, Usuario.verificaTipoIdentificacao(email.getEmail()));

        CPF cpf = new CPF("244.440.690-71");
        assertEquals(ValoresPadrao.CPF, Usuario.verificaTipoIdentificacao(cpf.getCpf()));
    }
}