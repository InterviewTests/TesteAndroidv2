package br.com.altran.santander.davidmelo.ui.base;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
}

