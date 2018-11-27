package com.rafaelpereiraramos.testeandroidv2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafaelpereiraramos.testeandroidv2.db.model.ParameterTO

/**
 * Created by Rafael P. Ramos on 26/11/2018.
 */
@Dao
interface ParameterDao {

    @Query("SELECT * FROM ParameterTO WHERE `key` = :key")
    fun getParameter(key: String): LiveData<ParameterTO?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setParameter(parameter: ParameterTO)
}