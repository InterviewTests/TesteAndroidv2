package br.com.fernandodutra.testeandroidv2.activities;

import br.com.fernandodutra.testeandroidv2.models.StatementListResponse;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;


/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 23/06/2019
 * Time: 20:38
 * TesteAndroidv2
 */
public interface StatementListContract {

    public interface Model {

        interface OnFinished {
            void onFinished(StatementListResponse statementListResponse);

            void onFailure(Throwable t);
        }

        void getStatementList(final OnFinished onFinishedListener, Integer userId);
    }

    public interface View {

        void setStatementListResponseToRecyclerView(StatementListResponse statementListResponse);

        void onResponseFailure(Throwable t);

        void returnLogin();

        void showMessage(String message);
    }

    public interface Presenter {

        void getStatementList(Integer userId);

        StatementListResponse getStatementListResponse();

        void setStatementListResponse(StatementListResponse statementListResponse);

        StatementListResponse getStatementResponse();

        void setUserAccount(UserAccount userAccount);

        UserAccount findUserAccount(Integer userId);
    }

}
