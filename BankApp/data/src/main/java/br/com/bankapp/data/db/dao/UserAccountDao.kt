package br.com.bankapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.bankapp.data.db.entity.UserAccountEntity
import br.com.bankapp.data.extensions.getDistinct

@Dao
abstract class UserAccountDao {

    @Query("SELECT * FROM user_account WHERE user_id = :userId")
    protected abstract fun getUserById(userId: Int): LiveData<UserAccountEntity>

    fun getUserByIdDistinct(userId: Int): LiveData<UserAccountEntity> = getUserById(userId).getDistinct()

    @Transaction
    open suspend fun clearAndInsert(userAccountEntity: UserAccountEntity) {
        delete()
        insert(userAccountEntity)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(userAccountEntity: UserAccountEntity)

    @Query("DELETE FROM user_account")
    abstract suspend fun delete()
}