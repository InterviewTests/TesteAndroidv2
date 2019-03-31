package br.com.rms.bankapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.rms.bankapp.data.local.database.entity.Statement

@Dao
interface StatementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(statement: Statement)
}