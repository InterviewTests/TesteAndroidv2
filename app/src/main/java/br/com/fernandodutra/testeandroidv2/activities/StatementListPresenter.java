package br.com.fernandodutra.testeandroidv2.activities;

import br.com.fernandodutra.testeandroidv2.models.StatementListResponse;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 23/06/2019
 * Time: 20:42
 * TesteAndroidv2
 */
public class StatementListPresenter implements StatementListContract.Presenter, StatementListContract.Model.OnFinished {

    private StatementListContract.View view;
    private StatementListModel statementListModel;
    private StatementListResponse statementListResponse;
    private UserAccountModel userAccountModel;

    public StatementListPresenter(StatementListContract.View view,
                                  StatementListModel statementListModel,
                                  UserAccountModel userAccountModel) {
        this.view = view;
        this.statementListModel = statementListModel;
        this.userAccountModel = userAccountModel;
    }


    @Override
    public void onFinished(StatementListResponse statementListResponse) {
        this.setStatementListResponse(statementListResponse);
        view.setStatementListResponseToRecyclerView(statementListResponse);
    }

    @Override
    public void onFailure(Throwable t) {
        view.onResponseFailure(t);
    }

    @Override
    public void getStatementList(Integer userId) {
        statementListModel.getStatementList(this, userId);
    }

    @Override
    public StatementListResponse getStatementListResponse() {
        return this.statementListResponse;
    }

    @Override
    public void setStatementListResponse(StatementListResponse statementListResponse) {
        this.statementListResponse = statementListResponse;
    }

    @Override
    public StatementListResponse getStatementResponse() {
        return this.statementListResponse;
    }

    @Override
    public void setUserAccount(UserAccount userAccount) {
        this.userAccountModel = userAccountModel;
    }

    @Override
    public UserAccount findUserAccount(Integer userId) {
        return userAccountModel.findById(userId);
    }
}
