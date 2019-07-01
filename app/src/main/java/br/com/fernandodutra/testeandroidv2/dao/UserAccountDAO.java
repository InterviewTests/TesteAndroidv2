package br.com.fernandodutra.testeandroidv2.dao;

import br.com.fernandodutra.testeandroidv2.models.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 17/06/2019
 * Time: 10:59
 * TesteAndroidv2
 */
public interface UserAccountDAO<T> {

    void insert(UserAccount useraccount) throws SQLException;

    void update(UserAccount useraccount) throws SQLException;

    void delete(Integer userId) throws SQLException;

    UserAccount findByID(Integer userId) throws SQLException;

    ArrayList<HMAux> findList() throws SQLException;

    int nextID() throws SQLException;

}
