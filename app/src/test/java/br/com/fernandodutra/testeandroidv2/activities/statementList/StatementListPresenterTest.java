package br.com.fernandodutra.testeandroidv2.activities.statementList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.ArrayList;

import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 30/06/2019
 * Time: 01:18
 * TesteAndroidv2_CleanCode
 */
public class StatementListPresenterTest {

    private StatementListWorker statementListWorker;

    @Before
    public void setUp() throws Exception {
        this.statementListWorker = new StatementListWorker();
    }

    @Test
    public void statementListPresenterTest_IsValidData() {
        //Given
        this.statementListWorker.setStatementList(new ArrayList<StatementList>());
        this.statementListWorker.getStatementList().add(new StatementList("Pagamento",
                "Conta de telefone",
                "2018-10-17",
                -760d,
                ""));
        this.statementListWorker.getStatementList().add(new StatementList("TED Enviada",
                "Roberto da Luz",
                "2018-07-27",
                -35.67d,
                ""));
        this.statementListWorker.getStatementList().add(new StatementList("Pagamento",
                "Boleto",
                "2018-08-01",
                -200d,
                ""));
        this.statementListWorker.getStatementList().add(new StatementList("TED Recebida",
                "Salário",
                "2018-08-21",
                1400.5d,
                ""));

        StatementListPresenter statementListPresenter = new StatementListPresenter();
        StatementListResponse statementListResponse = new StatementListResponse();

        statementListResponse.statementListWorker = this.statementListWorker;

        StatementListActivityInputSpy statementListActivityInputSpy = new StatementListActivityInputSpy();
        statementListPresenter.statementListActivityInputWeakReference = new WeakReference<StatementListActivityInput>(statementListActivityInputSpy);

        //When
        statementListPresenter.presentLoginMetaData(statementListResponse);

        //Then
        StatementListViewModel statementListViewModel = statementListActivityInputSpy.statementListViewModel;
        StatementListWorker statementListWorker = statementListViewModel.statementListWorker;

        if (statementListWorker.getStatementList().size() > 0) {
            Assert.assertTrue(statementListWorker.getStatementList().get(0).getDate().equals("17/10/2018"));
            Assert.assertTrue(statementListWorker.getStatementList().get(1).getDate().equals("27/07/2018"));
            Assert.assertTrue(statementListWorker.getStatementList().get(2).getDate().equals("01/08/2018"));
            Assert.assertTrue(statementListWorker.getStatementList().get(3).getDate().equals("21/08/2018"));
        }
    }

    @Test
    public void statementListPresenterTest_IsFormatedValue() {
        //Given
        this.statementListWorker.setStatementList(new ArrayList<StatementList>());
        this.statementListWorker.getStatementList().add(new StatementList("Pagamento",
                "Conta de telefone",
                "2018-10-17",
                -760d,
                ""));
        this.statementListWorker.getStatementList().add(new StatementList("TED Enviada",
                "Roberto da Luz",
                "2018-07-27",
                -35.67d,
                ""));
        this.statementListWorker.getStatementList().add(new StatementList("Pagamento",
                "Boleto",
                "2018-08-01",
                -200d,
                ""));
        this.statementListWorker.getStatementList().add(new StatementList("TED Recebida",
                "Salário",
                "2018-08-21",
                1400.5d,
                ""));

        StatementListPresenter statementListPresenter = new StatementListPresenter();
        StatementListResponse statementListResponse = new StatementListResponse();

        statementListResponse.statementListWorker = this.statementListWorker;

        StatementListActivityInputSpy statementListActivityInputSpy = new StatementListActivityInputSpy();
        statementListPresenter.statementListActivityInputWeakReference = new WeakReference<StatementListActivityInput>(statementListActivityInputSpy);

        //When
        statementListPresenter.presentLoginMetaData(statementListResponse);

        //Then
        StatementListViewModel statementListViewModel = statementListActivityInputSpy.statementListViewModel;
        StatementListWorker statementListWorker = statementListViewModel.statementListWorker;

        if (statementListWorker.getStatementList().size() > 0) {
            Assert.assertTrue(statementListWorker.getStatementList().get(0).getFormatedValue().equals("-R$ 760,00"));
            Assert.assertTrue(statementListWorker.getStatementList().get(1).getFormatedValue().equals("-R$ 35,67"));
            Assert.assertTrue(statementListWorker.getStatementList().get(2).getFormatedValue().equals("-R$ 200,00"));
            Assert.assertTrue(statementListWorker.getStatementList().get(3).getFormatedValue().equals("R$ 1.400,50"));
        }
    }


    private class StatementListActivityInputSpy implements StatementListActivityInput {
        public boolean isdisplayStatementListMetaDataCalled = false;
        public StatementListViewModel statementListViewModel;

        @Override
        public void displayLoginMetaData(StatementListViewModel statementListViewModel) {
            this.statementListViewModel = statementListViewModel;
        }
    }

}