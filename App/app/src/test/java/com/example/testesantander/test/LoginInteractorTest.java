package com.example.testesantander.test;

import com.example.testesantander.LoginActivity;

import org.junit.Test;

import Interactors.LoginInteractor;
import Models.CPF;
import Models.Email;
import Models.Senha;
import Models.Usuario;
import Presenters.LoginPresenter;

import static org.junit.Assert.*;

public class LoginInteractorTest {

    @Test
    public void cria_usuario_valido(){
        LoginPresenter presenter = new LoginPresenter(new LoginActivity());
        LoginInteractor interactor = new LoginInteractor(presenter);
        interactor.criaUsuario("244.440.690-74", "Test@1");
        assertEquals(true, interactor.validaUsuario());

        interactor.criaUsuario("test_user@mail.com", "Test@1");
        assertEquals(true, interactor.validaUsuario());
    }

    @Test
    public void barra_usuario_invalido(){
        LoginPresenter presenter = new LoginPresenter(new LoginActivity());
        LoginInteractor interactor = new LoginInteractor(presenter);
        interactor.criaUsuario("244.440.690-4", "Test@1");
        assertEquals(false, interactor.validaUsuario());

        interactor.criaUsuario("test_user@mai", "Test@1");
        assertEquals(false, interactor.validaUsuario());
    }
}