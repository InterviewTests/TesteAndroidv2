package teste.santander.com.santander;

import java.lang.ref.WeakReference;

public enum CurrencyConfigurator {
    INSTANCE;
    public void configure(Currency activity){

        CurrencyPresenter presenter = new CurrencyPresenter();
        presenter.output = new WeakReference<CurrencyInput>(activity);

        CurrencyInteractor interactor = new CurrencyInteractor();
        interactor.output = presenter;
        interactor.activity = new WeakReference<>(activity);

        if (activity.output == null){
            activity.output = interactor;
        }

    }
}
