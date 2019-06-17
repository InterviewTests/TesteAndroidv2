package com.accenture.project.apptesteandroid.bankStatement;

import java.lang.ref.WeakReference;

/**
 Classe que configura instâncias necessárias para o módulo de BankStatement
 */

public enum BankStatementConfigurator {

    GET_INSTANCE;

    public void configure(BankStatementActivity bankStatementActivity) {

        BankStatementPresenter bankStatementPresenter = new BankStatementPresenter();
        bankStatementPresenter.iStatementActivity = new WeakReference<IBankStatementActivity>(bankStatementActivity);


        BankStatementInteractor bankStatementInteractor = new BankStatementInteractor();
        bankStatementInteractor.iBankStatementPresenter = bankStatementPresenter;

        if (bankStatementActivity.bankStatementInteractor == null) {
            bankStatementActivity.bankStatementInteractor = bankStatementInteractor;
        }

    }

}
