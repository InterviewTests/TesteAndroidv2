package br.com.bankapp.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import br.com.bankapp.data.db.entity.StatementEntity

@Dao
abstract class  StatementDao {

    @Query("select * from statements WHERE user_id = :userId ORDER BY date DESC")
    abstract fun getStatements(userId: Int): DataSource.Factory<Int, StatementEntity>

    @Transaction
    open suspend fun clearAndInsert(statements: List<StatementEntity>) {
        delete()
        insert(statements)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(statements: List<StatementEntity>)

    @Query("DELETE FROM statements")
    abstract suspend fun delete()
}