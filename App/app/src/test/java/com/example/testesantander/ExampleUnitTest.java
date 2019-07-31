package com.example.testesantander;

import org.junit.Test;

import Controladores.ControleLogin;
import Modelos.Usuario;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
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