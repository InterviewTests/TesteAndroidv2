package com.example.testesantander;

import org.junit.Test;

import Controladores.ControleLogin;
import Modelos.Usuario;

import static org.junit.Assert.*;

public class ControleLoginTest {
    private Usuario user;
    private ControleLogin controlador;

    private void testaValidacao(String login, String senha, Boolean resultado){
        user = new Usuario(login, senha);
        controlador = new ControleLogin(user);
        assertEquals(resultado, controlador.validaUsuario());
    }

    @Test
    public void validacao_cpf() {
        // CPF com letras -> false
        testaValidacao("43125csca","Senha_123", false);

        // CPF sem 11 numeros -> false
        testaValidacao("43","Senha_123", false);

        // CPF com numeracao invalida -> false
        testaValidacao("529.982.247-32", "Senha_123", false);

        // CPF válido -> true
        testaValidacao("529.982.247-25", "Senha_123", true);
        testaValidacao("52998224725", "Senha_123", true);
    }

    @Test
    public void validacao_email(){
        // Email invalido
        testaValidacao("@mail.com", "Senha_123", false);
        testaValidacao("UsuAri0_1@.com", "Senha_123", false);

        // Email válido
        testaValidacao("Usuario_1@mail.com", "Senha_123", true);
        testaValidacao("Usuario.1.Enge@mail.com", "Senha_123", true);
        testaValidacao("UsuAri0_1@mail.com", "Senha_123", true);
        testaValidacao("usuario@mail.com", "Senha_123", true);
    }

    @Test
    public void validacao_senha(){
        // Senha sem letras maiusculas
        testaValidacao("usuario@mail.com", "senha_123", false);

        // Senha sem caracteres especiais
        testaValidacao("usuario@mail.com", "Senha123", false);

        // Senha sem números
        testaValidacao("usuario@mail.com", "Senha", false);

        // Senha válida
        testaValidacao("usuario@mail.com", "Senha_123", true);
    }

    @Test
    public void nao_autentica_campos_em_branco(){
        // Login em branco
        testaValidacao("","435", false);

        // Senha em branco
        testaValidacao("43125csca","", false);

        // Ambos campos em branco
        testaValidacao("","", false);
    }
}