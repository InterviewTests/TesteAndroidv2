package com.resourceit.app.dao;

import com.resourceit.app.models.LoginModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LoginDao {

    @Query("SELECT * FROM users")
    List<LoginModel> getAll();

    @Query("SELECT * FROM users WHERE userId = :userId")
    LoginModel findById(int userId);

    @Query("DELETE FROM users")
    void deleteall();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(LoginModel... login);

    @Delete
    void delete(LoginModel login);

}
