package teste.santander.com.santander;

import java.lang.ref.WeakReference;

interface CurrencyPresenterInput {
    void presentCurrencyMetaData(StatementAdapter statementAdapter);
}

public class CurrencyPresenter implements CurrencyPresenterInput {

    public WeakReference<CurrencyInput> output;
    @Override
    public void presentCurrencyMetaData(StatementAdapter statementAdapter) {
        output.get().displayCurrencyMetaData(statementAdapter);
    }
}
