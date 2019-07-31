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

    @Test
    public void validacao_cpf() {
        // CPF com letras -> false
        user = new Usuario("43125csca","435");
        updateControlador();
        assertEquals(false, controlador.validaUsuario());

        // CPF sem 11 numeros -> false
        user = new Usuario("43","435");
        updateControlador();
        assertEquals(false, controlador.validaUsuario());
    }

    @Test
    public void nao_autentica_campos_em_branco(){
        // Login em branco
        user = new Usuario("","435");
        updateControlador();
        assertEquals(false, controlador.validaUsuario());

        // Senha em branco
        user = new Usuario("15szc","");
        updateControlador();
        assertEquals(false, controlador.validaUsuario());

        // Ambos campos em branco
        user = new Usuario("","");
        updateControlador();
        assertEquals(false, controlador.validaUsuario());
    }
}