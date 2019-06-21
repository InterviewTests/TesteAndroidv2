package com.example.bankapp.domain;

import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.dashboard.StatementListModel;

public class DashboardDomain {

    private String title;
    private String desc;
    private String date;
    private double value;

    public DashboardContract.IRepository repository;

    public void getList(long id, final BaseCallback<StatementListModel> listener){
        repository.getList(id, new BaseCallback<StatementListModel>() {
            @Override
            public void onSuccessful(StatementListModel value) {
                listener.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String text) {
                listener.onUnsuccessful(text);
            }
        });
    }

}
