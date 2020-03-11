package com.test.banktest.homeScreen;

import com.test.banktest.model.StatementModel;
import com.test.banktest.model.UserModel;
import com.test.banktest.worker.serviceWorker.ServiceError;

import java.util.List;

public class HomeResponse {
    public UserModel user;
    public List<StatementModel> statementList;
    public ServiceError error;
}
