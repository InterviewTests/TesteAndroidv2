package com.example.testeandroidv2.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NAME_DB = "DB_TESTEV2";
    public static String TABLE_USUARIO = "usuario";

    public DbHelper(Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS " + TABLE_USUARIO+
                " (" +
                "userId INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "login CHAR(10) NOT NULL, " +
                "lastPassword CHAR(10) NOT NULL, " +
                "bankAccount CHAR(4) DEFAULT NULL, " +
                "agency CHAR(9) DEFAULT NULL, " +
                "loginData DATETIME DEFAULT NULL " +
                ")";

        try {

            sqLiteDatabase.execSQL(sqlUsuarios);
            Log.i("INFO DB", "Sucesso ao criar a tabelas!");

        }
        catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar a tabelas: " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // qdo é feita a atualização do app
        String sqlUsuarios = "DROP TABLE IF EXISTS " + TABLE_USUARIO + ";";
        try {

            sqLiteDatabase.execSQL(sqlUsuarios);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB", "Sucesso ao ATUALIZAR APP!");

        }
        catch (Exception e) {
            Log.i("INFO DB", "Erro ao ATUALIZAR APP: " + e.getMessage());
        }

    }
}
