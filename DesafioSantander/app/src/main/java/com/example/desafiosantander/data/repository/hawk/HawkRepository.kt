package com.example.desafiosantander.data.repository.hawk

import com.orhanobut.hawk.Hawk

class HawkRepository : HawkContract {

    override fun save(key: String, any: Any?) = Hawk.put(key, any)

    override fun getObject(key: String): Any = Hawk.get(key)

    override fun delete() = Hawk.deleteAll()

    override fun contains(key: String) = Hawk.contains(key)

}