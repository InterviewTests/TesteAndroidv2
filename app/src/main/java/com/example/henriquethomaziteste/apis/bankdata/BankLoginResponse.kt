package com.example.henriquethomaziteste.apis.bankdata

data class BankLoginResponse(

    var userAccount: BankUserData? = BankUserData(),
    var error: Any?
){

}