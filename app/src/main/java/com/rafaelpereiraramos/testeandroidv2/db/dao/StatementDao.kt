package com.rafaelpereiraramos.testeandroidv2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafaelpereiraramos.testeandroidv2.db.model.StatementTO

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Dao
interface StatementDao {

    @Query("SELECT * FROM StatementTO WHERE user_id = :id")
    fun getStatementsByUserId(id: Int): LiveData<List<StatementTO>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(statementTO: StatementTO)
}