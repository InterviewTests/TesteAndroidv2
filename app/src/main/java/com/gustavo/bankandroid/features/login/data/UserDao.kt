package com.gustavo.bankandroid.features.login.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gustavo.bankandroid.features.login.data.dto.UserInfoDto
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(userInfo: UserInfoDto)

    @Query("SELECT * FROM user")
    fun getUserInfo(): Single<UserInfoDto>
}