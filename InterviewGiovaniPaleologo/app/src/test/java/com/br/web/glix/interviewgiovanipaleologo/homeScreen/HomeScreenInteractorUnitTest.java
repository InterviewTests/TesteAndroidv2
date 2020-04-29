package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import androidx.annotation.NonNull;

import com.br.web.glix.interviewgiovanipaleologo.models.Statement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class HomeScreenInteractorUnitTest {

    @Test
    public void HomeScreen_showStatementList_Worker(){
        //Given
        HomeScreenInteractor homeScreenInteractor = new HomeScreenInteractor();
        HomeScreenRequest homeScreenRequest = new HomeScreenRequest();
        homeScreenRequest.userId = "1";

        homeScreenInteractor.homeScreenPresenterInput = new HomeScreenPresenterInputSpy();
        StatementWorkerInputSpy statementWorkerInputSpy = new StatementWorkerInputSpy();
        homeScreenInteractor.showStatementList(statementWorkerInputSpy.getStatementList());

        //When
        homeScreenInteractor.getStatementList(homeScreenRequest);

        //Then
        Assert.assertTrue("Statement List", statementWorkerInputSpy.isgetStatementListMethodCalled);
    }

    private class StatementWorkerInputSpy implements StatementWorkerInput {
        boolean isgetStatementListMethodCalled = false;

        @NonNull
        private List<Statement> getStatements() {
            List<Statement> statementList = new ArrayList<>();

            Statement item1 = new Statement();
            item1.setTitle("Pagamento");
            item1.setDate("17/10/2018");
            item1.setDesc("Conta de telefone");
            item1.setValue(250.25);
            statementList.add(item1);

            return statementList;
        }

        @Override
        public List<Statement> getStatementList() {
            isgetStatementListMethodCalled = true;
            return getStatements();
        }
    }

    private class HomeScreenPresenterInputSpy implements HomeScreenPresenterInput {
        boolean presentHomeScreenIsCalled = false;
        HomeScreenResponse homeScreenResponseCopy;
        List<Statement> returnStatementList = new ArrayList<>();

        @Override
        public void showStatementList(HomeScreenResponse homeScreenResponse) {
            presentHomeScreenIsCalled = true;
            homeScreenResponseCopy = homeScreenResponse;
        }

        @Override
        public void showStatementList(List<Statement> statementList) {
            presentHomeScreenIsCalled = true;
            returnStatementList = statementList;
        }
    }

}
