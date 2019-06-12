package br.com.douglas.fukuhara.bank.home.configurator;

import java.lang.ref.WeakReference;

import br.com.douglas.fukuhara.bank.home.Contract;
import br.com.douglas.fukuhara.bank.home.interactor.HomeInteractor;
import br.com.douglas.fukuhara.bank.home.presenter.HomePresenter;
import br.com.douglas.fukuhara.bank.home.router.HomeRouter;
import br.com.douglas.fukuhara.bank.home.ui.HomeActivity;
import br.com.douglas.fukuhara.bank.network.RetrofitImpl;

public final class HomeConfigurator {
    public static void configure(HomeActivity activity) {

        // Creates the Router and set a weak reference of the activity in it
        HomeRouter router = new HomeRouter();
        router.setActivity(new WeakReference<HomeActivity>(activity));

        // Creates the Presenter and set a weak reference of the activity interface in it
        HomePresenter presenter = new HomePresenter();
        presenter.setOutput(new WeakReference<Contract.HomeActivityInput>(activity));


        // Creates the Interactor and set a reference of the presenter in it
        HomeInteractor interactor = new HomeInteractor(RetrofitImpl.getInstance());
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
