package com.bilulo.androidtest04.list;

import com.bilulo.androidtest04.data.model.ErrorModel;
import com.bilulo.androidtest04.data.model.StatementModel;
import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.StatementsResponse;
import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.ui.list.presenter.ListPresenter;
import com.bilulo.androidtest04.ui.list.view.adapter.StatementsAdapter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ListPresenterTest {
    private StatementsResponse validResponse;
    private StatementsResponse invalidResponse;
    private UserAccountModel validUserAccountModel;
    private UserAccountModel invalidUserAccountModel;
    @Before
    public void setTestVariables() {
        validResponse = new StatementsResponse();
        validResponse.setErrorModel(null);
        List<StatementModel> statementModelList = new ArrayList<>();
        String[] datesArray = new String[]{"2018-08-15", "2018-02-23", "2018-06-29", "2018-01-02", "2018-04-09"};
        BigDecimal[] valuesArray = new BigDecimal[]{
                new BigDecimal(-133.33), new BigDecimal(228.343), new BigDecimal(-30),
                new BigDecimal(666.661), new BigDecimal(420.22)};
        for (int x = 0; x < 5; x++) {
            StatementModel statementModel = new StatementModel();
            statementModel.setTitle("teste");
            statementModel.setDesc("teste");
            statementModel.setDate(datesArray[x]);
            statementModel.setValue(valuesArray[x]);
            statementModelList.add(x, statementModel);
        }
        validResponse.setStatementModelList(statementModelList);


        invalidResponse = new StatementsResponse();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(1);
        errorModel.setMessage("Error");
        invalidResponse.setErrorModel(errorModel);
        
        validUserAccountModel = new UserAccountModel();
        validUserAccountModel.setAgency("012314564");
        validUserAccountModel.setBalance(BigDecimal.valueOf(3123.33455));
        validUserAccountModel.setName("Pedro Alvares Cabral");
        validUserAccountModel.setUserId(2);
        validUserAccountModel.setBankAccount("3333");

        invalidUserAccountModel = new UserAccountModel();
        invalidUserAccountModel.setAgency("012314564");
        invalidUserAccountModel.setBalance(BigDecimal.valueOf(3123.33455));
        invalidUserAccountModel.setName(null);
        invalidUserAccountModel.setBankAccount("");
    }

    @Test
    public void setData_shouldCall_activityLoadUserAccountData_AND_activityLoadStatements_withVALIDResponse_AND_SUCCESSFULRequest() {
        ListPresenter presenter = new ListPresenter();
        ListPresenterTest.ListActivitySpy listActivitySpy = new ListPresenterTest.ListActivitySpy();
        presenter.activity = new WeakReference<ListContract.ActivityContract>(listActivitySpy);
        presenter.setData(validUserAccountModel, validResponse, true);
        Assert.assertTrue(listActivitySpy.isLoadStatementsCalled);
        Assert.assertTrue(listActivitySpy.isLoadUserAccountCalled);
    }

    @Test
    public void setData_shouldNOTCall_activityLoadUserAccountData_withINVALIDUserAccountParameters() {
        ListPresenter presenter = new ListPresenter();
        ListPresenterTest.ListActivitySpy listActivitySpy = new ListPresenterTest.ListActivitySpy();
        presenter.activity = new WeakReference<ListContract.ActivityContract>(listActivitySpy);
        presenter.setData(invalidUserAccountModel, validResponse, true);
        Assert.assertFalse(listActivitySpy.isLoadUserAccountCalled);
    }

    @Test
    public void setData_shouldNOTCall_activityLoadStatements_withVALIDResponse_AND_UNSUCCESSFULRequest() {
        ListPresenter presenter = new ListPresenter();
        ListPresenterTest.ListActivitySpy listActivitySpy = new ListPresenterTest.ListActivitySpy();
        presenter.activity = new WeakReference<ListContract.ActivityContract>(listActivitySpy);
        presenter.setData(validUserAccountModel, validResponse, false);
        Assert.assertFalse(listActivitySpy.isLoadStatementsCalled);
        Assert.assertTrue(listActivitySpy.isDisplayErrorCalled);
    }

    @Test
    public void setData_shouldNOTCall_activityLoadStatements_withINVALIDResponse_AND_SUCCESSFULRequest() {
        ListPresenter presenter = new ListPresenter();
        ListPresenterTest.ListActivitySpy listActivitySpy = new ListPresenterTest.ListActivitySpy();
        presenter.activity = new WeakReference<ListContract.ActivityContract>(listActivitySpy);
        presenter.setData(validUserAccountModel, invalidResponse, true);
        Assert.assertFalse(listActivitySpy.isLoadStatementsCalled);
        Assert.assertTrue(listActivitySpy.isDisplayErrorCalled);
    }

    @Test
    public void setData_shouldNOTCall_activityLoadStatements_withINVALIDResponse_AND_UNSUCCESSFULRequest() {
        ListPresenter presenter = new ListPresenter();
        ListPresenterTest.ListActivitySpy listActivitySpy = new ListPresenterTest.ListActivitySpy();
        presenter.activity = new WeakReference<ListContract.ActivityContract>(listActivitySpy);
        presenter.setData(validUserAccountModel, invalidResponse, false);
        Assert.assertFalse(listActivitySpy.isLoadStatementsCalled);
        Assert.assertTrue(listActivitySpy.isDisplayErrorCalled);
    }

    class ListActivitySpy implements ListContract.ActivityContract {
        boolean isLoadUserAccountCalled = false;
        boolean isLoadStatementsCalled = false;
        boolean isDisplayErrorCalled = false;
        
        @Override
        public void loadUserAccountData(String name, String account, String balance) {
            isLoadUserAccountCalled = true;
        }

        @Override
        public void loadStatements(StatementsAdapter adapter) {
            isLoadStatementsCalled = true;
        }

        @Override
        public void displayError() {
            isDisplayErrorCalled = true;
        }
    }
}
