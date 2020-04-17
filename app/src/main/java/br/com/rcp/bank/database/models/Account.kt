package br.com.rcp.bank.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
	indices = [
		Index(name = "user_account_idx",value = ["identifier"])
	])
data class Account(
	@PrimaryKey	var		identifier	: Long		= 0,
	@ColumnInfo var		name		: String	= "",
	@ColumnInfo	var		account		: String	= "",
	@ColumnInfo	var		agency		: String	= "",
	@ColumnInfo	var		balance		: Double	= 0.0
)