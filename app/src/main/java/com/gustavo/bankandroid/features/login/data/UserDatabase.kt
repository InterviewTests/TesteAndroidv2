package com.gustavo.bankandroid.features.login.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gustavo.bankandroid.features.login.data.dto.UserInfoDto

@Database(entities = [UserInfoDto::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}