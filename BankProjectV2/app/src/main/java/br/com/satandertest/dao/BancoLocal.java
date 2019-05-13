package br.com.satandertest.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.satandertest.models.UserAccount;

/**
 * Created by ProgramacaoIII on 03/10/2016.
 */

public class BancoLocal extends SQLiteOpenHelper {

    private static final String DATABASE = "dbSatanderTest";
    private static final int VERSION = 1;


    //Metódo de criação do Banco
    //Atentar a passagem da variavel VERSION:
    //Banco é criado automaticamente com o número da versão
    //Para alterar a versão muda-se a version para que entre no método onUpgrade
    public BancoLocal(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String userAccount = "CREATE TABLE userAccount " +
                " ( " +
                " user_id INTEGER PRIMARY KEY NOT NULL," +
                " name TEXT NULL," +
                " bankAccount TEXT NULL," +
                " agency TEXT NULL," +
                " balance TEXT NULL" +
                " );";
        db.execSQL(userAccount);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Em caso de alteração do banco
    }

    public long saveUserAccount(UserAccount userAccount) {
        ContentValues values = new ContentValues();

        values.put("user_id", userAccount.getUserId());
        values.put("name", userAccount.getName());
        values.put("bankAccount", userAccount.getBankAccount());
        values.put("agency", userAccount.getAgency());
        values.put("balance", userAccount.getBalance());

        return getWritableDatabase().insert("userAccount", null, values);

    }

    public void alterarUserAccount(UserAccount userAccount) {
        ContentValues values = new ContentValues();

        values.put("user_id", userAccount.getUserId());
        values.put("name", userAccount.getName());
        values.put("bankAccount", userAccount.getBankAccount());
        values.put("agency", userAccount.getAgency());
        values.put("balance", userAccount.getBalance());
        ;

        String[] args = {userAccount.getUserId().toString()};

        try {
            if (getWritableDatabase() != null) {
                getWritableDatabase().update("userAccount", values, "user_id=?", args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserAccount(UserAccount userAccount) {

        String[] args = {userAccount.getUserId().toString()};
        getWritableDatabase().delete("userAccount", "user_id=?", args);

    }

    public void cleanDb() {
        getWritableDatabase().execSQL("delete from userAccount");
    }

    public UserAccount getUserAccount() {

        Cursor cursor = getWritableDatabase()
                .rawQuery("SELECT * from userAccount;", null);

        UserAccount u = new UserAccount();

        if (cursor.getCount() != 0) {

            cursor.moveToNext();

            u.setUserId(cursor.getInt(0));
            u.setName(cursor.getString(1));
            u.setBankAccount(cursor.getString(2));
            u.setAgency(cursor.getString(3));
            u.setBalance(cursor.getString(4));

            getWritableDatabase().close();

            return u;
        }

        getWritableDatabase().close();

        return null;
    }

}

