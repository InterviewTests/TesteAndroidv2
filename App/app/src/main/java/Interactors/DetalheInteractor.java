package Interactors;

import Presenters.DetalhePresenterInput;

public class DetalheInteractor {
    private int userId;
    private DetalhePresenterInput presenter;

    public DetalheInteractor(DetalhePresenterInput presenter) {
        this.presenter = presenter;
    }

    public void getDetalhes(int userId){
        presenter.criaListaDetalhes();
    }
}
