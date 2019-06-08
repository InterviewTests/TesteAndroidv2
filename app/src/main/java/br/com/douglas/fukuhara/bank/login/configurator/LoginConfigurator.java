package br.com.douglas.fukuhara.bank.login.configurator;

import java.lang.ref.WeakReference;

import br.com.douglas.fukuhara.bank.login.Contract;
import br.com.douglas.fukuhara.bank.login.interactor.LoginInteractor;
import br.com.douglas.fukuhara.bank.login.presenter.LoginPresenter;
import br.com.douglas.fukuhara.bank.login.router.LoginRouter;
import br.com.douglas.fukuhara.bank.login.ui.LoginActivity;

public final class LoginConfigurator {
    public static void configure(LoginActivity activity) {

        // Creates the Router and set a weak reference of the activity in it
        LoginRouter router = new LoginRouter();
        router.setActivity(new WeakReference<>(activity));

        // Creates the Presenter and set a weak reference of the activity interface in it
        LoginPresenter presenter = new LoginPresenter();
        presenter.setOutput(new WeakReference<Contract.LoginActivityInput>(activity));

        // Creates the Interactor and set a reference of the presenter in it
        LoginInteractor interactor = new LoginInteractor();
        interactor.setPresenter(presenter);

        // If not yet set, configures LoginInteractor as the Output for Activity
        if (activity.getOutput() == null) {
            activity.setOutput(interactor);
        }
        // If not yet set, configures LoginRouter as the Router for Activity
        if (activity.getRouter() == null) {
            activity.setRouter(router);
        }
    }
}
