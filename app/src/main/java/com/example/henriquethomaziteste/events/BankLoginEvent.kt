package com.example.henriquethomaziteste.events

import com.example.henriquethomaziteste.apis.bankdata.BankUserData

class BankLoginEvent(
    var error: Boolean? = false,
    var userData: BankUserData? = BankUserData()
){
}