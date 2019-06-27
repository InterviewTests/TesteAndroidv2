package com.bilulo.androidtest04.list;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.ui.list.interactor.ListInteractor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ListInteractorTest {
    private UserAccountModel validUserAccountModel;
    private UserAccountModel invalidUserAccountModel;
    
    @Before
    public void setTestVariables() {
        validUserAccountModel = new UserAccountModel();
        validUserAccountModel.setAgency("012314588");
        validUserAccountModel.setBalance(BigDecimal.valueOf(3123.33455));
        validUserAccountModel.setName("Pedro Alvares Cabral");
        validUserAccountModel.setUserId(2);
        validUserAccountModel.setBankAccount("3333");

        invalidUserAccountModel = new UserAccountModel();
        invalidUserAccountModel.setAgency("0123145884");
        invalidUserAccountModel.setBalance(BigDecimal.valueOf(3123.33455));
        invalidUserAccountModel.setName("Pedro Alvares Cabral");
        invalidUserAccountModel.setBankAccount("3333");
    }

    @Test
    public void performList_shouldCall_workerPerformList_withVALIDParameters() {
        ListInteractor interactor = new ListInteractor();
        ListWorkerSpy listWorkerSpy = new ListWorkerSpy();
        interactor.worker = listWorkerSpy;
        interactor.fetchAndLoadData(validUserAccountModel);
        Assert.assertTrue(listWorkerSpy.isGetStatementsCalled);
    }

    @Test
    public void performList_shouldNOTCall_workerPerformList_withINVALIDParameters() {
        ListInteractor interactor = new ListInteractor();
        ListWorkerSpy listWorkerSpy = new ListWorkerSpy();
        interactor.worker = listWorkerSpy;
        interactor.fetchAndLoadData(invalidUserAccountModel);
        Assert.assertFalse(listWorkerSpy.isGetStatementsCalled);
    }

    class ListWorkerSpy implements ListContract.WorkerContract {
        ListInteractor listInteractor;
        boolean isGetStatementsCalled = false;

        @Override
        public void getStatements(int userId) {
            isGetStatementsCalled = true;
        }
    }
}
