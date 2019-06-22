package com.example.bankapp.ui.dashboard;

import com.example.bankapp.model.dashboard.StatementList;

import java.util.List;

public class DashboardViewPresenter {

    public interface dashboardView{
        void showList(List<StatementList> list);

        void showProgress(boolean key);

        void showMessageError(String text);
    }

    public interface dashboardPresenter{
        void getList(long id);
    }
}
