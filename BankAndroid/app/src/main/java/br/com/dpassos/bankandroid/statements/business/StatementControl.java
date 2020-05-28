package br.com.dpassos.bankandroid.statements.business;

import java.util.List;
import br.com.dpassos.bankandroid.login.business.UserAccount;
import br.com.dpassos.bankandroid.login.data.LoginRepository;
import br.com.dpassos.bankandroid.statements.data.Factory;
import br.com.dpassos.bankandroid.statements.data.StatementRepository;
import br.com.dpassos.bankandroid.statements.data.StatementRequest;

public class StatementControl {

    public enum StatementStatus{
        USER_NO_LOGED, NO_STATEMENTS
    }
    public static class StatementException extends Exception{
        public StatementStatus status;

        StatementException(StatementStatus loginStatus) {
            super(loginStatus.name());
            this.status = loginStatus;
        }
    }

    public List<Statement> getStatements(UserAccount userAccount) throws StatementException {
        StatementRepository repository = new Factory().getRepositoryImpl();
        StatementRequest request = new StatementRequest();
        request.id = userAccount.userId;
        List<Statement> statementList = repository.getStatements(request);

        if(statementList == null || statementList.isEmpty()) {
            throw new StatementException(StatementStatus.NO_STATEMENTS);
        }
        return statementList;
    }

    public UserAccount getCurrentAccout() throws StatementException {
        LoginRepository loginRepository = new br.com.dpassos.bankandroid.login.data.Factory().getRepositoryImpl();
        UserAccount userAccount = loginRepository.getCurrentAccount();
        if(userAccount == null) {
            throw new StatementException(StatementStatus.USER_NO_LOGED);
        }
        return userAccount;
    }
}
