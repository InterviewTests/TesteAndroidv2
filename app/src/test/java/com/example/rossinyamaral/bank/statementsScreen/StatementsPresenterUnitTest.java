package com.example.rossinyamaral.bank.statementsScreen;

import com.example.rossinyamaral.bank.model.StatementModel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
public class StatementsPresenterUnitTest {

    @Test
    public void presentData_with_validInput_shouldCall_displayData() {
        //Given
        StatementsPresenter presenter = new StatementsPresenter();
        StatementsResponse response = new StatementsResponse();
        response.statements = new ArrayList<>();
        StatementModel statementModel = new StatementModel();
        statementModel.title = "test";
        statementModel.desc = "test";
        statementModel.date = "2000-01-01";
        statementModel.value = 10;
        response.statements.add(statementModel);

        StatementsActivityInputSpy activityInputSpy = new StatementsActivityInputSpy();
        presenter.output = new WeakReference<StatementsActivityInput>(activityInputSpy);

        //When
        presenter.presentStatementsData(response);

        //Then
        Assert.assertTrue("When the valid input is passed to Presenter " +
                        "Then displayData should be called",
                activityInputSpy.isDisplayDataCalled);
    }

    @Test
    public void presentData_with_inValidInput_shouldNotCall_displayData() {
        //Given
        StatementsPresenter presenter = new StatementsPresenter();

        StatementsActivityInputSpy activityInputSpy = new StatementsActivityInputSpy();
        presenter.output = new WeakReference<StatementsActivityInput>(activityInputSpy);

        //When
        presenter.presentStatementsData(null);

        //Then
        Assert.assertFalse("When the valid input is passed to LoginPresenter " +
                        "Then displayLoginMetaData should NOT be called",
                activityInputSpy.isDisplayDataCalled);
    }

    private class StatementsActivityInputSpy implements StatementsActivityInput {
        boolean isDisplayDataCalled = false;
        StatementsViewModel viewModelCopy;
        @Override
        public void displayStatementsData(StatementsViewModel viewModel) {
            isDisplayDataCalled = true;
            viewModelCopy = viewModel;
        }
    }
}
