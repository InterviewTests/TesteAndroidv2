package com.gustavo.bankandroid.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gustavo.bankandroid.datasource.database.dto.UserInfoDto

@Database(entities = [UserInfoDto::class], version = 2)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDao: UserDao
}