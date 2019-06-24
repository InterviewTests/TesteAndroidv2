package com.example.bankapp;

import com.example.bankapp.data.remote.model.dashboard.StatementListModel;
import com.example.bankapp.data.repository.DashboardRepository;
import com.example.bankapp.domain.DashboardDomain;
import com.example.bankapp.helper.BaseCallback;

import junit.framework.TestCase;

import org.junit.Assert;

public class DashboardTest extends TestCase {


    public void testListIsEmpty(){
        DashboardDomain dashboardDomain = new DashboardDomain();
        dashboardDomain.repository = new DashboardRepository();
        dashboardDomain.getList(-1, new BaseCallback<StatementListModel>() {
            @Override
            public void onSuccessful(StatementListModel value) {
                Assert.assertEquals(null,value);
            }

            @Override
            public void onUnsuccessful(String text) {

            }
        });
    }
}
