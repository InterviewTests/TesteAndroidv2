package br.com.fernandodutra.testeandroidv2.activities.statementList;

import java.lang.ref.WeakReference;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 20:02
 * TesteAndroidv2_CleanCode
 */
public enum StatementListConfigurator {
    INSTANCE;
    public void configure(StatementListActivity activity) {

        StatementListRouter router = new StatementListRouter();
        router.activity = new WeakReference<>(activity);

        StatementListPresenter presenter = new StatementListPresenter();
        presenter.statementListActivityInputWeakReference = new WeakReference<StatementListActivityInput>(activity);

        StatementListInteractor interactor = new StatementListInteractor();
        interactor.statementListPresenterInput = presenter;

        if (activity.statementListInteractorInput == null) {
            activity.statementListInteractorInput = interactor;
        }
        if (activity.statementListRouter == null) {
            activity.statementListRouter = router;
        }
    }
}
