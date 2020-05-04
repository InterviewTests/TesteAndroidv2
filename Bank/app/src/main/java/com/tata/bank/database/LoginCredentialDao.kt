package com.tata.bank.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe

@Dao
interface LoginCredentialDao {

    @Query("SELECT * FROM login_credentials")
    fun getCredentials(): Maybe<Array<LoginCredentialEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(credentials: LoginCredentialEntity) : Maybe<Long>
}