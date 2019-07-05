package ssilvalucas.testeandroidv2.screen.login;

import java.lang.ref.WeakReference;

public enum LoginConfigurator {
    INSTANCE;

    public void configure(LoginActivity activity){

        LoginRouter router = new LoginRouter();
        router.acticity = new WeakReference<>(activity);

        LoginInteractor interactor = new LoginInteractor();


        LoginPresenter presenter = new LoginPresenter();

    }
}
