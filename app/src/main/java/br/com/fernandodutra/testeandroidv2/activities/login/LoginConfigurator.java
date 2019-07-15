package br.com.fernandodutra.testeandroidv2.activities.login;

import java.lang.ref.WeakReference;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 26/06/2019
 * Time: 21:16
 * TesteAndroidv2_CleanCode
 */
public enum LoginConfigurator {
    INSTANCE;
    public void configure(LoginActivity activity) {

        LoginRouter router = new LoginRouter();
        router.activity = new WeakReference<>(activity);

        LoginPresenter presenter = new LoginPresenter();
        presenter.loginActivityInputWeakReference = new WeakReference<LoginActivityInput>(activity);

        LoginInteractor interactor = new LoginInteractor();
        interactor.loginPresenterInput = presenter;

        if (activity.loginInteractorInput == null) {
            activity.loginInteractorInput = interactor;
        }
        if (activity.loginRouter == null) {
            activity.loginRouter = router;
        }
    }
}
