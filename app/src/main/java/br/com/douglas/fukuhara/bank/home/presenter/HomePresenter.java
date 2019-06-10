package br.com.douglas.fukuhara.bank.home.presenter;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.List;

import br.com.douglas.fukuhara.bank.R;
import br.com.douglas.fukuhara.bank.home.Contract;
import br.com.douglas.fukuhara.bank.network.vo.StatementListVo;

import static br.com.douglas.fukuhara.bank.utils.FormaterUtils.formatAgencyWithMask;
import static br.com.douglas.fukuhara.bank.utils.FormaterUtils.formatBankAccountAndAgency;
import static br.com.douglas.fukuhara.bank.utils.FormaterUtils.includeCurrencyInValue;

public class HomePresenter implements Contract.HomePresenterInput {

    private WeakReference<Contract.HomeActivityInput> mOutput;

    public void setOutput(WeakReference<Contract.HomeActivityInput> output) {
        mOutput = output;
    }

    private Contract.HomeActivityInput getOutput() {
        return mOutput.get();
    }


    @Override
    public void noUserInfo() {
        if (getOutput() != null) {
            getOutput().setHeaderInformation("-", "-", "-");
        }
    }

    @Override
    public void setNoUserDataAvailable() {
        if (getOutput() != null) {
            getOutput().hideContentLoader();
            getOutput().hideUserDataContainer();
            getOutput().showNoDataAvailable();
        }
    }

    @Override
    public void setHomeHeaderInfo(String name, String agency, String bankAccount, BigDecimal balance) {
        if (getOutput() != null) {

            if (agency.length() > 8) {
                agency = formatAgencyWithMask(agency);
            }

            String account = formatBankAccountAndAgency(agency, bankAccount);
            String formattedBalance = includeCurrencyInValue(balance);

            getOutput().setHeaderInformation(name, account, formattedBalance);
        }
    }

    @Override
    public void showUserDataErrorMessage(String message) {
        if (getOutput() != null) {
            getOutput().hideContentLoader();
            getOutput().hideUserDataContainer();
            getOutput().showNoDataAvailable();
            getOutput().notifyErrorToUser(message);
        }
    }

    @Override
    public void showUserDataGenericError() {
        if (getOutput() != null) {
            getOutput().hideContentLoader();
            getOutput().hideUserDataContainer();
            getOutput().showNoDataAvailable();
            getOutput().notifyResourceErrorToUser(R.string.login_generic_error);
        }
    }

    @Override
    public void setUserDataInfo(List<StatementListVo.StatementItem> statementList) {
        if (getOutput() != null) {
            getOutput().setRecentList(statementList);
            getOutput().hideContentLoader();
            getOutput().showUserDataContainer();
            getOutput().hideNoDataAvailable();
        }
    }

    @Override
    public void onDataFetch() {
        if (getOutput() != null) {
            getOutput().showContentLoader();
            getOutput().hideUserDataContainer();
            getOutput().hideNoDataAvailable();
        }
    }
}
