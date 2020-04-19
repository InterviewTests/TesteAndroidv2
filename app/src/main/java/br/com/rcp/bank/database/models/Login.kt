package br.com.rcp.bank.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
	indices = [
		Index(name = "login_idx",value = ["identifier"])
	])
data class Login(
	@PrimaryKey	val		identifier	: Long		= 1,
	@ColumnInfo var		user		: String	= ""
)