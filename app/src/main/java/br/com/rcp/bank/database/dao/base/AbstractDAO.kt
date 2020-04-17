package br.com.rcp.bank.database.dao.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

abstract class BaseDAO<T> {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun save(entity : T)

	@Delete
	abstract fun remove(entity : T)
}