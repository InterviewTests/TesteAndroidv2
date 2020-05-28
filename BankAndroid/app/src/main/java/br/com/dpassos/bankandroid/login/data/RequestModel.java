package br.com.dpassos.bankandroid.login.data;

import br.com.dpassos.bankandroid.login.business.UserAccount;

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
    public UserAccount userAccount;
    public Error error;
}



class Error {
    int code;
    String message;
}
