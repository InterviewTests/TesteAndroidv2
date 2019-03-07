package br.com.kakobotasso.testeandroidv2.currency;

import java.lang.ref.WeakReference;

interface CurrencyRouterInput {
}

public class CurrencyRouter implements CurrencyRouterInput {
    public WeakReference<CurrencyActivity> activity;
}
