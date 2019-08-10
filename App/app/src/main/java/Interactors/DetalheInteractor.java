package Interactors;

import Presenters.DetalhePresenterInput;
import Services.DetalheTask;

public class DetalheInteractor {
    private DetalhePresenterInput presenter;

    public DetalheInteractor(DetalhePresenterInput presenter) {
        this.presenter = presenter;
    }

    public void getDetalhes(int userId){
        DetalheTask task = new DetalheTask(presenter);
        task.setParametros(userId);
        task.execute();
    }
}
