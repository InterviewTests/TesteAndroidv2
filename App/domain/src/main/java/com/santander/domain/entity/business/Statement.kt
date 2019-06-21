package com.santander.domain.entity.business

data class Statement(
	val date: String,
	val title: String,
	val value: Money,
	val desc: String
)