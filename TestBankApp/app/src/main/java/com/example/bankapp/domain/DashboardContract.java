package com.example.bankapp.domain;

import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.dashboard.statementListModel;

public class DashboardContract {

    public interface IRepository{
        void getList(long id, BaseCallback<statementListModel> result);
    }
}
