package com.accenture.santander.dataManager.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.accenture.santander.dataManager.dao.UserDAO
import com.accenture.santander.dataManager.entity.UserEntity

@Database(entities = arrayOf(
        UserEntity::class),
        version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    companion object {
        val DATABASE_NAME = "SantanderApp"
    }

    abstract fun userDAO(): UserDAO
}