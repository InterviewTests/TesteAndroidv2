package br.com.rms.bankapp.data.local.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rms.bankapp.data.local.database.entity.Account


@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: Account)

    @Query("SELECT * FROM account WHERE userId = :accountId")
    fun selectAccount(accountId: Int?): Account


}