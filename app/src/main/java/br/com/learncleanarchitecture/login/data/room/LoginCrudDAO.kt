package br.com.learncleanarchitecture.login.data.room

import androidx.room.*

@Dao
interface LoginCrudDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loginEntity: LoginEntity) : Long

    @Query("DELETE FROM login_table")
    fun deleteAll()

    @Query("SELECT * from login_table")
    fun getAllLogin(): List<LoginEntity>

    @Query("SELECT * from login_table ORDER BY userId DESC LIMIT 1")
    fun getLastLogin(): LoginEntity?

    @Query(
        "SELECT * from login_table WHERE name = :name")
    fun getByName(name: String): List<LoginEntity>

    @Update
    fun updateLogin(vararg loginEntity: LoginEntity)

    @Delete
    fun deleteLogin(vararg loginEntity: LoginEntity)
}