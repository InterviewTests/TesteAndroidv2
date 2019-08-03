package com.example.testesantander;

import org.junit.Test;

import Controladores.ControleLogin;
import Modelos.Usuario;

import static org.junit.Assert.*;

public class ControleLoginTest {
    private Usuario user;
    private ControleLogin controlador;

    private void updateControlador(){
        controlador = new ControleLogin(user);
    }
    private void testaValidacao(String login, String senha, Boolean resultado){
        user = new Usuario(login, senha);
        controlador = new ControleLogin(user);
        assertEquals(resultado, controlador.validaUsuario());
    }

    @Test
    public void validacao_cpf() {
        // CPF com letras -> false
        testaValidacao("43125csca","435", false);

        // CPF sem 11 numeros -> false
        testaValidacao("43","435", false);

        // CPF com numeracao invalida -> false
        testaValidacao("529.982.247-32", "efs", false);

        // CPF válido -> true
        testaValidacao("529.982.247-25", "efs", true);
        testaValidacao("52998224725", "efs", true);
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