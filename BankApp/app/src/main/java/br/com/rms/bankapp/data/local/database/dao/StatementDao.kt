package br.com.rms.bankapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rms.bankapp.data.local.database.entity.Account
import br.com.rms.bankapp.data.local.database.entity.Statement

@Dao
abstract class StatementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     abstract fun insert(statement: List<Statement>)

    @Query("SELECT * FROM statement")
    abstract fun selectAllStatement(): List<Statement>
}