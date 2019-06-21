package com.santander.domain.entity.business

data class UserAccount(
	val bankAccount: BankAccount,
	val balance: Money,
	val name: String,
	val userId: Int
)