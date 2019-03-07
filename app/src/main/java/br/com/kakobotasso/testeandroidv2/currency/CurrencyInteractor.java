package br.com.kakobotasso.testeandroidv2.currency;

interface CurrencyInteractorInput {
    void fetchCurrencyData(String userId);
}

public class CurrencyInteractor implements CurrencyInteractorInput {
    public CurrencyPresenterInput output;
    private CurrencyWorkerInput currencyWorkerInput;

    public CurrencyWorkerInput getCurrencyWorkerInput() {
        if(currencyWorkerInput == null) {
            return new CurrencyWorker();
        }

        return currencyWorkerInput;
    }

    public void setCurrencyWorkerInput(CurrencyWorkerInput currencyWorkerInput) {
        this.currencyWorkerInput = currencyWorkerInput;
    }

    @Override
    public void fetchCurrencyData(String userId) {
        currencyWorkerInput = getCurrencyWorkerInput();
        final CurrencyResponse currencyResponse = new CurrencyResponse();

        output.showCurrencyInfo(currencyResponse);
    }
}
