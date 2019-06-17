package com.accenture.project.apptesteandroid.bankStatement;

import com.accenture.project.apptesteandroid.model.BankStatement;
import com.accenture.project.apptesteandroid.model.ErrorMessages;

import java.util.List;

/**
  Recebe uma ação da activity e devolve os dados ao presenter
 */
interface IBankStatementInteractor {

    void fetchBankStatementByUserId(int userId);

    void fetchBankStatementByUserIdResponseOk(List<BankStatement> bankStatementList);

    void fetchBankStatementByUserIdResponseError();
}

public class BankStatementInteractor implements IBankStatementInteractor {

    public IBankStatementPresenter iBankStatementPresenter;

    @Override
    public void fetchBankStatementByUserId(int userId) {
        BankStatementRepository bankStatementRepository = new BankStatementRepository();
        bankStatementRepository.getBankStatement(userId, this);

    }

    @Override
    public void fetchBankStatementByUserIdResponseOk(List<BankStatement> bankStatementList) {

        if (bankStatementList != null) {
            iBankStatementPresenter.presentBankStatement(bankStatementList);
        }
    }

    @Override
    public void fetchBankStatementByUserIdResponseError() {
        iBankStatementPresenter.presentMessage(ErrorMessages.ERROR_CONNECTION);
    }
}
