package com.bilulo.androidtest04.list;

import androidx.test.rule.ActivityTestRule;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.StatementsResponse;
import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.ui.list.view.ListActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ListActivityTest {

    @Rule
    public ActivityTestRule<ListActivity> rule = new ActivityTestRule<>(ListActivity.class);

    @Test
    public void onCreate_shouldCall_interactorFetchAndLoadData() {
        ListActivity activity = rule.getActivity();
        ListActivityTest.ListInteractorSpy listInteractorSpy = new ListActivityTest.ListInteractorSpy();
        activity.interactor = listInteractorSpy;
        activity.fetchAndLoadData();
        Assert.assertTrue(listInteractorSpy.isFetchAndLoadDataCalled);
    }

    class ListInteractorSpy implements ListContract.InteractorContract {
        boolean isFetchAndLoadDataCalled = false;
        boolean isSetStatementsResponseCalled = false;

        @Override
        public void fetchAndLoadData(UserAccountModel userAccountModel) {
            isFetchAndLoadDataCalled = true;
        }

        @Override
        public void setStatementsResponse(StatementsResponse response, boolean isSuccessful) {
            isSetStatementsResponseCalled = true;
        }
    }
}
