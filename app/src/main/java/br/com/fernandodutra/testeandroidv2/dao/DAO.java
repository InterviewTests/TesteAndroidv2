package br.com.fernandodutra.testeandroidv2.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.fernandodutra.testeandroidv2.utils.Constants;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 17/06/2019
 * Time: 10:42
 * TesteAndroidv2
 */
public class DAO {

    private Context context;
    protected SQLiteDatabase db;

    public DAO(Context context) {
        this.context = context;
    }

    public void openDataBase() {
        SQLiteHelper vHelper = new SQLiteHelper(
                context,
                Constants.DATABASE,
                null,
                Constants.VERSION
        );

        this.db = vHelper.getWritableDatabase(); // solicitacao de banco validas.
    }

    public void closeDataBase() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
