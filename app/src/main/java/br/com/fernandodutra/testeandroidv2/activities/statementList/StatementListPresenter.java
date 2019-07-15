package br.com.fernandodutra.testeandroidv2.activities.statementList;

import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.ArrayList;

import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 20:03
 * TesteAndroidv2_CleanCode
 */

interface StatementListPresenterInput {
    void presentLoginMetaData(StatementListResponse statementListResponse);
}

public class StatementListPresenter implements StatementListPresenterInput {

    public WeakReference<StatementListActivityInput> statementListActivityInputWeakReference;
    //
    public static String TAG = StatementListPresenter.class.getSimpleName();


    @Override
    public void presentLoginMetaData(StatementListResponse statementListResponse) {

        StatementListViewModel statementListViewModel = new StatementListViewModel();
        statementListViewModel.statementListWorker = new StatementListWorker();

        if (statementListResponse.errorMessage.size() == 0) {
            statementListViewModel.statementListWorker = new StatementListWorker();
            statementListViewModel.statementListWorker.setStatementList(new ArrayList<StatementList>());

            for (StatementList sl : statementListResponse.statementListWorker.getStatementList()) {

                String dataFormated = sl.getDate().substring(8, 10) + "/" +
                        sl.getDate().substring(5, 7) + "/" +
                        sl.getDate().substring(0, 4);

                NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
                String valueFormated = numberFormat.format(sl.getValue());

                StatementList statementList = new StatementList();
                statementList.setDesc(sl.getDesc());
                statementList.setTitle(sl.getTitle());
                statementList.setDate(dataFormated);
                statementList.setValue(sl.getValue());
                statementList.setFormatedValue(valueFormated);

                statementListViewModel.statementListWorker.getStatementList().add(statementList);
            }

            statementListViewModel.errorMessage = statementListResponse.errorMessage;
        } else {
            statementListViewModel.statementListWorker = statementListResponse.statementListWorker;
            statementListViewModel.errorMessage = new ArrayList<>();
        }

        statementListActivityInputWeakReference.get().displayLoginMetaData(statementListViewModel);
    }

}
