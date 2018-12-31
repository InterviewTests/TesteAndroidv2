package com.home.project.testeandroidv2.bankStatemenScreen;

import java.lang.ref.WeakReference;

public enum BankStatementConfigurator {

    /*
    Classe que obtém a instância de outras classes que referenciam a BankStatementActivity
     */

    INSTANCE;

    public void configure(BankStatementActivity activity) {

        BankStatementRouter router = new BankStatementRouter();
        router.activity = new WeakReference<>(activity);

        if (activity.router == null) {
            activity.router = router;
        }
    }
}
