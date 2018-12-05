package com.br.natanfelipe.bankapp.model

import java.io.Serializable

class UserAccount (var userId:Int,var name:String,var bankAccount:String,var agency:Int, var balance:Double) : Serializable {
}