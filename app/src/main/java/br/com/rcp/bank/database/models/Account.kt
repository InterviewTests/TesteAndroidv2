package br.com.rcp.bank.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
	indices = [
		Index(name = "user_account_idx",value = ["identifier"])
	])
data class Account(
	@PrimaryKey	val		identifier	: Long		= 1,
	@ColumnInfo	var 	external	: Long		= 0,	// Service ID
	@ColumnInfo var		name		: String	= "",
	@ColumnInfo	var		account		: String	= "",
	@ColumnInfo	var		agency		: String	= "",
	@ColumnInfo	var		balance		: Double	= 0.0
): Serializable