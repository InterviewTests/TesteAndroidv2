package com.ivan.bankapp.view.presentation;

import com.ivan.bankapp.api.NetworkManager;
import com.ivan.bankapp.api.NetworkResponseListener;
import com.ivan.bankapp.model.StatementList;
import com.ivan.bankapp.model.User;

import java.lang.ref.WeakReference;

class Presenter implements ViewContract.IPresenter {

    private WeakReference<ViewContract.IView> view;

    Presenter(ViewContract.IView view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void onHomeLoaded() {
        NetworkManager.getInstance().getStatements(new NetworkResponseListener<StatementList>() {
            @Override
            public void onResponseReceived(StatementList s) {

                for (int i = 0; i<s.getStatementList().length; i++) {

                    view.get().getStatements(s.getStatementList()[i].getTittle(),
                            s.getStatementList()[i].getDesc(),
                            s.getStatementList()[i].getDate(),
                            s.getStatementList()[i].getValue());
                }
            }

            @Override
            public void onError() {
            }
        });
    }

    @Override
    public void onLogin() {

        NetworkManager.getInstance().login(new NetworkResponseListener<User>() {
            @Override
            public void onResponseReceived(User user) {

                view.get().login(user.getUserAccount().getId(), user.getUserAccount().getName(),user.getUserAccount().getBankAccount(), user.getUserAccount().getAgency(),user.getUserAccount().getBalance());
            }

            @Override
            public void onError() {
            }
        });

    }
}