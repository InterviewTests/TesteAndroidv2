package com.example.thiagoevoa.bank.util

import okhttp3.MediaType

const val URL_LOGIN = "https://bank-app-test.herokuapp.com/api/login"
const val URL_STATEMENTS = " https://bank-app-test.herokuapp.com/api/statements/"

const val USER = "user"
const val USER_LOGGED = "user"
const val SHARED_PREFERENCES = "sharedPreference"

val CONTENT_TYPE_JSON = MediaType.parse("application/json; charset=utf-8")
