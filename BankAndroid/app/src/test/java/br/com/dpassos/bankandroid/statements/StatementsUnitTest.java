package br.com.dpassos.bankandroid.statements;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import br.com.dpassos.bankandroid.infra.Requester;
import br.com.dpassos.bankandroid.login.business.LoginControl;
import br.com.dpassos.bankandroid.login.business.UserAccount;
import br.com.dpassos.bankandroid.login.data.LoginRepository;
import br.com.dpassos.bankandroid.login.data.LoginRequest;
import br.com.dpassos.bankandroid.login.data.SimpleLoginRepository;
import br.com.dpassos.bankandroid.statements.business.Statement;
import br.com.dpassos.bankandroid.statements.business.StatementControl;
import br.com.dpassos.bankandroid.statements.data.Factory;
import br.com.dpassos.bankandroid.statements.data.SimpleStatementRepository;
import br.com.dpassos.bankandroid.statements.data.StatementRepository;
import br.com.dpassos.bankandroid.statements.data.StatementRequest;

public class StatementsUnitTest {

    /**
     * Apesar de nao ser ferramenta especifica para testes de componentes podemos sim fazer alguns testes
     * de unidades que já indicam que partes do sistema que consomem dados estao trabalhando corretamente
     * esses testes sao muito rapidos de fazer e manter.
     * Assim como a execucao de uma bateria de testes sao rapidas de executar.
     */
    @Test
    public void statementEndpointTest() {
        Requester requester = new Requester();
        String noErrorResponse = "\"error\":{}";
        String response = null;
        try {
            response = requester.doGet("https://bank-app-test.herokuapp.com/api/statements/1");
        } catch (Exception e) {}
        Assert.assertNotNull(response);
        Assert.assertTrue(response.contains(noErrorResponse));
    }

    @Test
    public void statementsRepositoryTest() {

        StatementRequest request = new StatementRequest();
        request.id = "1";
        StatementRepository repository = new Factory().getRepositoryImpl();
        List<Statement> statements = repository.getStatements(request);
        Assert.assertNotNull(statements);
        Assert.assertFalse(statements.isEmpty());
    }

    @Test
    public void statementControlTest() {
        UserAccount userAccount = new UserAccount();
        userAccount.userId = "1";
        List<Statement> statements = null;
        StatementControl control = new StatementControl();

        try {
            statements = control.getStatements(userAccount);
        } catch (Exception e) {}
        Assert.assertNotNull(statements);
        Assert.assertFalse(statements.isEmpty());
    }
}
