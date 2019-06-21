package com.example.bankapp.ui.dashboard;

import com.example.bankapp.domain.DashboardDomain;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.dashboard.StatementListModel;
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

        dashboardDomain.getList(id, new BaseCallback<StatementListModel>() {
            @Override
            public void onSuccessful(StatementListModel value) {
                view.showList(value.getStatementList());
            }

            @Override
            public void onUnsuccessful(String text) {
                view.showMessageError(text);
            }
        });
    }
}
