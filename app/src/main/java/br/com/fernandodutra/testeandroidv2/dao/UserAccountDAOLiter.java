package br.com.fernandodutra.testeandroidv2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.utils.Constants;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 17/06/2019
 * Time: 11:08
 * TesteAndroidv2
 */
public class UserAccountDAOLiter extends DAO implements UserAccountDAO<UserAccount> {

    public UserAccountDAOLiter(Context context) {
        super(context);
    }

    @Override
    public void insert(UserAccount useraccount) throws SQLException {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();

            cv.put(Constants.USERACCOUNT_USERID, useraccount.getUserId());
            cv.put(Constants.USERACCOUNT_NAME, useraccount.getName());
            cv.put(Constants.USERACCOUNT_BANKACCOUNT, useraccount.getBankAccount());
            cv.put(Constants.USERACCOUNT_AGENCY, useraccount.getAgency());
            cv.put(Constants.USERACCOUNT_BALANCE, useraccount.getBalance());

            db.insert(Constants.USERACCOUNT_TABLE, null, cv);
        } finally {
            closeDataBase();
        }
    }

    @Override
    public void update(UserAccount useraccount) throws SQLException {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();

            String filter = " userId = ?";
            String[] arguments = {String.valueOf(useraccount.getUserId())};

            cv.put(Constants.USERACCOUNT_NAME, useraccount.getName());
            cv.put(Constants.USERACCOUNT_BANKACCOUNT, useraccount.getBankAccount());
            cv.put(Constants.USERACCOUNT_AGENCY, useraccount.getAgency());
            cv.put(Constants.USERACCOUNT_BALANCE, useraccount.getBalance());

            db.update(Constants.USERACCOUNT_TABLE, cv, filter, arguments);
        } finally {
            closeDataBase();
        }
    }

    @Override
    public void delete(Integer userId) throws SQLException {
        openDataBase();
        try {
            String filter = " userId = ?";
            String[] arguments = {String.valueOf(userId)};

            db.delete(Constants.USERACCOUNT_TABLE, filter, arguments);

        } finally {
            closeDataBase();
        }
    }

    @Override
    public UserAccount findByID(Integer userId) throws SQLException {
        openDataBase();
        //
        Cursor cursor = null;
        //
        UserAccount userAccount = null;
        //
        try {
            String sqlQuery = " select * from tb_useraccount where userId = ? ";
            String[] argumentos = {String.valueOf(userId)};

            cursor = db.rawQuery(sqlQuery, argumentos);

            while (cursor.moveToNext()) {
                userAccount = new UserAccount();
                userAccount.setUserId(cursor.getInt(cursor.getColumnIndex(Constants.USERACCOUNT_USERID)));
                userAccount.setName(cursor.getString(cursor.getColumnIndex(Constants.USERACCOUNT_NAME)));
                userAccount.setBankAccount(cursor.getString(cursor.getColumnIndex(Constants.USERACCOUNT_BANKACCOUNT)));
                userAccount.setAgency(cursor.getString(cursor.getColumnIndex(Constants.USERACCOUNT_AGENCY)));
                userAccount.setBalance(cursor.getDouble(cursor.getColumnIndex(Constants.USERACCOUNT_BALANCE)));
            }

            cursor.close();

        } finally {
            closeDataBase();
        }
        return userAccount;
    }

    @Override
    public ArrayList<HMAux> findList() throws SQLException {
        openDataBase();

        ArrayList<HMAux> userAccounts = new ArrayList<>();

        Cursor cursor = null;

        try {
            String sqlQuery = " select * from tb_useraccount ";

            cursor = db.rawQuery(sqlQuery, null);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();

                aux.put(Constants.USERACCOUNT_USERID, cursor.getString(cursor.getColumnIndex(Constants.USERACCOUNT_USERID)));
                aux.put(Constants.USERACCOUNT_NAME, cursor.getString(cursor.getColumnIndex(Constants.USERACCOUNT_NAME)));
                aux.put(Constants.USERACCOUNT_BANKACCOUNT, cursor.getString(cursor.getColumnIndex(Constants.USERACCOUNT_BANKACCOUNT)));
                aux.put(Constants.USERACCOUNT_AGENCY, cursor.getString(cursor.getColumnIndex(Constants.USERACCOUNT_AGENCY)));
                aux.put(Constants.USERACCOUNT_BALANCE, cursor.getString(cursor.getColumnIndex(Constants.USERACCOUNT_BALANCE)));

                userAccounts.add(aux);
            }

            cursor.close();

        } finally {
            closeDataBase();
        }
        return userAccounts;
    }

    @Override
    public long nextID() throws SQLException {
        long nextUserId = -1L;
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select ifnull(max(userId)+1,1) as userId from tb_useraccount; ";

            cursor = db.rawQuery(sqlQuery, null);

            while (cursor.moveToNext()) {
                nextUserId = cursor.getLong(cursor.getColumnIndex("userId"));
            }

            cursor.close();
        } finally {
            closeDataBase();
        }
        return nextUserId;
    }
}
