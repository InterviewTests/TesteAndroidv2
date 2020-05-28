package br.com.dpassos.bankandroid.statements.data;

import java.util.List;

import br.com.dpassos.bankandroid.statements.business.Statement;

/**
 * {
 *     "userAccount": {
 *         "userId": 1,
 *         "name": "Jose da Silva Teste",
 *         "bankAccount": "2050",
 *         "agency": "012314564",
 *         "balance": 3.3445
 *     },
 *     "error": {}
 * }
 */
public class RequestModel {
    public List<Statement> statementList;
    public Error error;
}


class Error {
    int code;
    String message;
}
