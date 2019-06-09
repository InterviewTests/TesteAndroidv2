package com.androiddeveloper.santanderTest.data.model.userdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(data: EncryptedData): Completable

    @Query("SELECT * FROM EncryptedData")
    fun getUser(): Flowable<EncryptedData>

    @Query("DELETE FROM EncryptedData")
    fun deleteUser(): Completable
}