package com.example.desafiosantander.data.repository.hawk

interface HawkContract {

    fun save(key: String, any: Any?): Boolean
    fun getObject(key: String): Any
    fun delete(): Boolean
    fun contains(key: String): Boolean
}