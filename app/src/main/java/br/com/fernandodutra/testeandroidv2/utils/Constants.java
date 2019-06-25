package br.com.fernandodutra.testeandroidv2.utils;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 17/06/2019
 * Time: 10:47
 * TesteAndroidv2
 */
public class Constants {

    // DATABASE
    public static final String DATABASE = "testeandroidv2.db";
    public static final int VERSION = 1;

    // LOGIN
    public static final String LOGIN = "login";
    public static final String USERACCOOUNT = "userAccoount";

    // TABLE - USERACCOUNT
    public static final String USERACCOUNT_TABLE = "tb_useraccount";
    public static final String USERACCOUNT_USERID = "userid";
    public static final String USERACCOUNT_NAME = "name";
    public static final String USERACCOUNT_BANKACCOUNT = "bankaccount";
    public static final String USERACCOUNT_AGENCY = "agency";
    public static final String USERACCOUNT_BALANCE = "balance";

    // TABLE - STATEMENTLIST
    public static final String STATEMENT_TABLE = "tb_statementlist";
    public static final String STATEMENT_STATEMENTLISTID = "statementlistid";
    public static final String STATEMENT_USERID = "userid";
    public static final String STATEMENT_TITLE = "title";
    public static final String STATEMENT_DESC = "desc";
    public static final String STATEMENT_DATE = "date";
    public static final String STATEMENT_VALUE = "value";

}
