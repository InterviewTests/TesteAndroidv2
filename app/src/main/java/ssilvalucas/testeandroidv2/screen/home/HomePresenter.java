package ssilvalucas.testeandroidv2.screen.home;

import java.lang.ref.WeakReference;
import ssilvalucas.testeandroidv2.data.model.StatementsResponse;

interface HomePresenterInput{
    void throwError(String errorMessage);
    void onSuccessfulFetchStatements(StatementsResponse response);
}

public class HomePresenter implements HomePresenterInput {
    public WeakReference<HomeActivity> output;

    @Override
    public void throwError(String errorMessage) {
        output.get().showErrorMessage(errorMessage);
    }

    @Override
    public void onSuccessfulFetchStatements(StatementsResponse response) {
        output.get().responseStatements(response);
    }
}
