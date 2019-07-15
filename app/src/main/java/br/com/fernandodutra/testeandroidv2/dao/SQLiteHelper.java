package br.com.fernandodutra.testeandroidv2.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 17/06/2019
 * Time: 10:44
 * TesteAndroidv2
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context,
                        String name,
                        SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE IF NOT EXISTS [tb_useraccount] \n" +
                    "  ( \n" +
                    "     userId       BIGINT NOT NULL, \n" +
                    "     name         TEXT, \n" +
                    "     bankAccount  TEXT, \n" +
                    "     agency       TEXT, \n" +
                    "     balance      DOUBLE, \n" +
                    "     CONSTRAINT [] PRIMARY KEY ([userId]) \n" +
                    "  ); ");

            sb.append("CREATE TABLE IF NOT EXISTS [tb_statementlist] \n" +
                    "  ( \n" +
                    "     statementListId  BIGINT NOT NULL, \n" +
                    "     userId           BIGINT NOT NULL, \n" +
                    "     title            TEXT, \n" +
                    "     desc             TEXT, \n" +
                    "     date             DATE, \n" +
                    "     value            DOUBLE, \n" +
                    "     CONSTRAINT [] PRIMARY KEY ([statementListId,userId]) \n" +
                    "  ); ");

            String[] sqlComands = sb.toString().split(";");

            for (int i = 0; i < sqlComands.length - 1; i++) {
                db.execSQL(sqlComands[i].toLowerCase());
            }

        } catch (SQLException e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("");

            String[] sqlComands = sb.toString().toLowerCase().split(";");

            for (int i = 0; i < sqlComands.length - 1; i++) {
                db.execSQL(sqlComands[i].toLowerCase());
            }

        } catch (SQLException e) {

        }
    }
}
