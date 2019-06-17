package com.accenture.santander.interector.dataManager.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.accenture.santander.interector.dataManager.dao.UserDAO
import com.accenture.santander.interector.dataManager.entity.UserEntity

@Database(entities = arrayOf(
        UserEntity::class),
        version = 1,
    exportSchema = false)
abstract class DataBase : RoomDatabase() {

    companion object {
        val DATABASE_NAME = "SantanderApp"
    }

    abstract fun userDAO(): UserDAO
}