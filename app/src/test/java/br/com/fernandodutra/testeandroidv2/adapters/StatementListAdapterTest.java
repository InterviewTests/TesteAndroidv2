package br.com.fernandodutra.testeandroidv2.adapters;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 30/06/2019
 * Time: 01:17
 * TesteAndroidv2_CleanCode
 */

@RunWith(RobolectricTestRunner.class)
public class StatementListAdapterTest {

    private StatementListAdapter statementListAdapter;
    private StatementListAdapter.StatementListHolder statementListHolder;

    @Before
    public void setUp() throws Exception {
        statementListAdapter = new StatementListAdapter();
    }

    @Test
    public void statementListAdapterTest_itemCount() {
        StatementListWorker statementListWorker = new StatementListWorker();

        statementListWorker.setStatementList(new ArrayList<StatementList>());
        statementListWorker.getStatementList().add(new StatementList("Pagamento",
                "Conta de telefone",
                "2018-10-17",
                -760d,
                ""));
        statementListWorker.getStatementList().add(new StatementList("TED Enviada",
                "Roberto da Luz",
                "2018-07-27",
                -35.67d,
                ""));
        statementListWorker.getStatementList().add(new StatementList("Pagamento",
                "Boleto",
                "2018-08-01",
                -200d,
                ""));
        statementListWorker.getStatementList().add(new StatementList("TED Recebida",
                "Salário",
                "2018-08-21",
                1400.5d,
                ""));

        statementListAdapter.setStatementListsModel(statementListWorker);

        assertTrue(statementListAdapter.getItemCount() == 4);
    }

}