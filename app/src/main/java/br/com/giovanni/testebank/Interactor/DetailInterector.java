package br.com.giovanni.testebank.Interactor;

import br.com.giovanni.testebank.Presenter.IDetailPresenter;
import br.com.giovanni.testebank.Helpers.DetailTask;

public class DetailInterector  {

    public IDetailPresenter setIntentDetail;

    public DetailInterector(IDetailPresenter setIntentDetail) {
        this.setIntentDetail = setIntentDetail;
    }

    public void getDetail (int userId){
        DetailTask statementsTask = new DetailTask(setIntentDetail);

        statementsTask.setUserId(userId);
        statementsTask.execute();
    }
}
