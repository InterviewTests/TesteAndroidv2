package com.example.bankapp.ui.dashboard;

import com.example.bankapp.domain.DashboardDomain;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.data.remote.model.dashboard.StatementListModel;
import com.example.bankapp.data.repository.DashboardRepository;

public class DashboardPresenter implements DashboardViewPresenter.dashboardPresenter {
    private DashboardViewPresenter.dashboardView view;

    public DashboardPresenter(DashboardViewPresenter.dashboardView view) {
        this.view = view;
    }

    @Override
    public void getList(long id) {
        view.showProgress(true);
        DashboardDomain dashboardDomain = new DashboardDomain();
        dashboardDomain.repository = new DashboardRepository();

        try {
            dashboardDomain.getList(id, new BaseCallback<StatementListModel>() {
                @Override
                public void onSuccessful(StatementListModel value) {
                    view.showProgress(false);
                    view.showList(value.getStatementList());
                }

                @Override
                public void onUnsuccessful(String text) {
                    view.showProgress(false);
                    view.showMessageError(text);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
