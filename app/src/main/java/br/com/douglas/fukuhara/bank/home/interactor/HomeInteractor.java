package br.com.douglas.fukuhara.bank.home.interactor;

import java.util.List;

import br.com.douglas.fukuhara.bank.home.Contract;
import br.com.douglas.fukuhara.bank.network.RestClient;
import br.com.douglas.fukuhara.bank.network.vo.StatementListVo;
import br.com.douglas.fukuhara.bank.network.vo.UserAccount;
import br.com.douglas.fukuhara.bank.network.vo.UserError;
import br.com.douglas.fukuhara.bank.utils.FormatterUtils;
import io.reactivex.disposables.CompositeDisposable;

import static br.com.douglas.fukuhara.bank.network.NetworkUtils.getObservableNetworkThread;

public class HomeInteractor implements Contract.HomeInteractorInput {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Contract.HomePresenterInput mOutput;
    private final RestClient mClient;
    private UserAccount mUserAccount;

    public HomeInteractor(RestClient client) {
        mClient = client;
    }

    public void setPresenter(Contract.HomePresenterInput presenter) {
        mOutput = presenter;
    }

    @Override
    public void setHomeHeader(UserAccount userAccount) {
        mUserAccount = userAccount;
        if (mUserAccount == null) {
            mOutput.noUserInfo();
            return;
        }
        mOutput.setHomeHeaderInfo(
                mUserAccount.getName(),
                mUserAccount.getAgency(),
                mUserAccount.getBankAccount(),
                mUserAccount.getBalance());
    }

    @Override
    public void fetchUserData() {
        if (mUserAccount == null) {
            mOutput.noUserInfo();
            return;
        }
        mOutput.onDataFetch();
        mCompositeDisposable.add(
                mClient.getApi().getUserData(mUserAccount.getUserId())
                .compose(getObservableNetworkThread())
                .subscribe(
                        this::onUserFetchResponse,
                        this::onUserFetchFailure));
    }

    private void onUserFetchResponse(StatementListVo statementListVo) {
        UserError userError = statementListVo.getUserError();
        if (userError != null) {
            String errorMsg = userError.getMessage();
            int errorCode = userError.getCode();

            if (errorMsg != null && !errorMsg.isEmpty() && errorCode != 0) {
                mOutput.showUserDataErrorMessage(FormatterUtils.formatLoginErrorMsg(errorMsg, errorCode));
                return;
            }
        }
        List<StatementListVo.StatementItem> statementList = statementListVo.getStatementList();
        if (statementList != null && statementList.size() > 0) {
            mOutput.setUserDataInfo(statementList);
        } else {
            mOutput.setNoUserDataAvailable();
        }
    }

    private void onUserFetchFailure(Throwable throwable) {
        String message = throwable.getMessage();
        if (message != null && !message.isEmpty()) {
            mOutput.showUserDataErrorMessage(message);
        } else {
            mOutput.showUserDataGenericError();
        }
    }

    @Override
    public void disposeAll() {
        mCompositeDisposable.clear();
    }
}
