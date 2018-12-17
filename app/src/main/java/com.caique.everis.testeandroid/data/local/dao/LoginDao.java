package com.caique.everis.testeandroid.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.caique.everis.testeandroid.model.Login;


@Dao
public interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Login... login);

    @Query("delete from login")
    void deleteAll();

    @Query("SELECT user, password from login " +
            "GROUP BY user, password")
    LiveData<Login> getLoginDao();
}

