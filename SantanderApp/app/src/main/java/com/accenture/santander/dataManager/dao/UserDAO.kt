package com.accenture.santander.dataManager.dao;

import androidx.room.Dao
import androidx.room.Query
import com.accenture.santander.dataManager.entity.UserEntity

@Dao
abstract class UserDAO : IDao<UserEntity>() {

    @Query("SELECT * FROM USER WHERE ID = :id")
    abstract fun findViewById(id: Int): UserEntity?

    @Query("SELECT * FROM USER")
    abstract fun find(): List<UserEntity>

    @Query("SELECT * FROM USER ORDER BY ID DESC LIMIT 1")
    abstract fun findDesc(): UserEntity?

}