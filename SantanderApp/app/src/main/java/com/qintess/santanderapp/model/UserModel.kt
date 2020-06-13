package com.qintess.santanderapp.model

data class UserModel (val userId: Int,
                      val name: String,
                      val bankAccount: String,
                      val agency: String,
                      val balance: Double)