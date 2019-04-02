package br.com.rms.bankapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rms.bankapp.data.local.database.entity.USER_ID
import br.com.rms.bankapp.data.local.database.entity.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id =$USER_ID")
    fun selectUser() : User

}