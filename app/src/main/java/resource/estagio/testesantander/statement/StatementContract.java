package resource.estagio.testesantander.statement;

import android.content.Context;

import java.util.List;

public class StatementContract {
    public interface View{

        Context getActivity();
        void showList(List<Statement> statementList);


    }
    public interface Presenter{
        void getStatement(long id);

    }
}
