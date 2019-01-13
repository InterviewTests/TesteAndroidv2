package com.resourceit.app.tools;

import com.resourceit.app.dao.LoginDao;
import com.resourceit.app.models.LoginModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {LoginModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LoginDao loginDao();
}
