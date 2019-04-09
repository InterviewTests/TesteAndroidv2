package br.com.rms.bankapp.data.local.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rms.bankapp.data.local.database.entity.Account


@Dao
abstract class AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAccount(account: Account)

    @Query("SELECT * FROM account WHERE userId = :accountId")
    abstract fun selectAccount(accountId: Int?): Account


}