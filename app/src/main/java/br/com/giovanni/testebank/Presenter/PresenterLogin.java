package br.com.giovanni.testebank.Presenter;

import br.com.giovanni.testebank.SetItentPrincipal;

public class PresenterLogin implements PresenterLoginImput {

    public SetItentPrincipal activity;

    public PresenterLogin (SetItentPrincipal activity){
        this.activity = activity;
    }

    @Override
    public void presenterLogin() {
        this.activity.intentPrincipal();
    }
}
