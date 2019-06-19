package com.example.bankapp.ui;

import com.example.bankapp.model.dashboard.statementList;

import java.util.List;

public class DashboardViewPresenter {

    public interface dashboardView{
        void showList(List<statementList> list);
    }

    public interface dashboardPresenter{
        void getList(long id);
    }
}
