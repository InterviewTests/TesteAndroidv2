package com.example.mybank.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mybank.data.local.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table")
    suspend fun clear();

    @Query("SELECT * FROM user_table")
    fun get() : LiveData<User>
}