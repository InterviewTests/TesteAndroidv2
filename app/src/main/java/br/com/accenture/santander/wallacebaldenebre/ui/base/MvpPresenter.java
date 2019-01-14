package br.com.accenture.santander.wallacebaldenebre.ui.base;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
}

