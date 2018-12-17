package com.caique.everis.testeandroid.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.caique.everis.testeandroid.data.local.dao.LoginDao;
import com.caique.everis.testeandroid.model.Login;

@Database(entities = {Login.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "songstats";
    private static AppDataBase instance;

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDataBase.class,
                        DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return instance;
    }

    public abstract LoginDao loginDao();
}
