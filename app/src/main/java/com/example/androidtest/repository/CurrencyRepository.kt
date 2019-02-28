package com.example.androidtest.repository

import java.util.*

object CurrencyRepository {

    fun getUserName() = "Jose da Silva Teste"
    fun getAccount() = "2050 / 01.111222-4"
    fun getBalance() = "R$ 1.000,00"

    fun getPayments(): List<Payment> {
        val result = ArrayList<Payment>()

        result.add(Payment("Compra", 534.99, Calendar.getInstance()))
        result.add(Payment("Compra", 534.99, Calendar.getInstance()))
        result.add(Payment("Compra", 534.99, Calendar.getInstance()))
        result.add(Payment("Compra", 534.99, Calendar.getInstance()))
        result.add(Payment("Compra", 534.99, Calendar.getInstance()))
        result.add(Payment("Compra", 534.99, Calendar.getInstance()))
        result.add(Payment("Compra", 534.99, Calendar.getInstance()))

        return result
    }


}