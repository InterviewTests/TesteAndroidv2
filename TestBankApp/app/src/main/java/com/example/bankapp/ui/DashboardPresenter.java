package com.example.bankapp.ui;

import com.example.bankapp.domain.DashboardDomain;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.dashboard.statementListModel;
import com.example.bankapp.repository.DashboardRepository;

public class DashboardPresenter implements DashboardViewPresenter.dashboardPresenter {
    private DashboardViewPresenter.dashboardView view;

    public DashboardPresenter(DashboardViewPresenter.dashboardView view) {
        this.view = view;
    }

    @Override
    public void getList(long id) {
        DashboardDomain dashboardDomain = new DashboardDomain();
        dashboardDomain.repository = new DashboardRepository();

        dashboardDomain.getList(id, new BaseCallback<statementListModel>() {
            @Override
            public void onSuccessful(statementListModel value) {
                view.showList(value.getStatementList());
            }

            @Override
            public void onUnsuccessful(String text) {

            }
        });
    }
}
