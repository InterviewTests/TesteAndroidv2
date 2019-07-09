package ssilvalucas.testeandroidv2.screen.home;

import java.lang.ref.WeakReference;

import ssilvalucas.testeandroidv2.data.model.StatementHome;
import ssilvalucas.testeandroidv2.data.model.StatementsResponse;
import ssilvalucas.testeandroidv2.util.FormatUtil;

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
        for (StatementHome statement: response.getStatementArrayList()) {
            statement.setFormatedDate(FormatUtil.formatDate(statement.getDate()));
            statement.setFormatedValue(FormatUtil.formatBalance(statement.getValue()));
        }
        output.get().showStatementsList(response.getStatementArrayList());
    }
}
