package com.bilulo.androidtest04.ui.list.presenter;

import com.bilulo.androidtest04.data.model.StatementModel;
import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.data.model.response.StatementsResponse;
import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.ui.list.view.adapter.StatementsAdapter;
import com.bilulo.androidtest04.utils.FormatUtil;
import com.bilulo.androidtest04.utils.ValidationUtil;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.List;

public class ListPresenter implements ListContract.PresenterContract {
    public WeakReference<ListContract.ActivityContract> activity;

    @Override
    public void setData(UserAccountModel userAccountModel, StatementsResponse response, boolean isSuccessful) {
        prepareAndSendData(userAccountModel, response, isSuccessful);
    }

    private void prepareAndSendData(UserAccountModel userAccountModel, StatementsResponse response, boolean isSuccessful) {
        if (userAccountModel != null) {
            String name = getName(userAccountModel);
            String account = getAccountFormatted(userAccountModel);
            String balance = getBalanceFormatted(userAccountModel);
            if (ValidationUtil.isValidString(name) && ValidationUtil.isValidString(account) && ValidationUtil.isValidString(balance))
                activity.get().loadUserAccountData(name, account, balance);
        }
        if (isSuccessful) {
            if (response != null) {
                List<StatementModel> statementModelList = response.getStatementModelList();
                if (statementModelList != null) {
                    StatementsAdapter statementsAdapter = new StatementsAdapter();
                    statementsAdapter.setData(statementModelList);
                    activity.get().loadStatements(statementsAdapter);
                } else {
                    activity.get().displayError();
                }
            } else {
                activity.get().displayError();
            }
        } else {
            activity.get().displayError();
        }
    }

    private String getBalanceFormatted(UserAccountModel userAccountModel) {
        BigDecimal balance = userAccountModel.getBalance();
        return FormatUtil.formatCurrency(balance);
    }


    private String getAccountFormatted(UserAccountModel userAccountModel) {
        String bankAccount = userAccountModel.getBankAccount();
        String agency = userAccountModel.getAgency();
        String accountInfo = FormatUtil.formatAccountNumber(bankAccount, agency);
        if (ValidationUtil.isValidString(accountInfo)) {
            return accountInfo;
        } else return null;
    }

    private String getName(UserAccountModel userAccountModel) {
        String name = userAccountModel.getName();
        if (ValidationUtil.isValidString(name)) {
            return name;
        } else return null;
    }
}
