package com.accenture.santander.dataManager.repository.deviceRepository

import android.content.Context
import com.accenture.santander.dataManager.dao.UserDAO
import com.accenture.santander.dataManager.entity.UserEntity
import com.accenture.santander.dataManager.repository.AbstractRepository
import com.google.gson.reflect.TypeToken

class UserRepository(context: Context) :
    AbstractRepository<UserDAO, UserEntity>(context, object : TypeToken<UserDAO>() {}.type), IUserRepository {
    override fun findViewById(id: Int): UserEntity? {
        return execute {
            _dao.findViewById(id)
        }
    }

    override fun find(): List<UserEntity>? {
        return execute {
            _dao.find()
        }
    }

    override fun findDesc(): UserEntity? {
        return execute {
            _dao.findDesc()
        }
    }

    override fun insert(type: UserEntity): Long? {
        return execute {
            _dao.insert(type)
        }
    }


    override fun update(type: UserEntity) {
        execute {
            _dao.update(type)
        }
    }

    override fun delete(type: UserEntity) {
        execute {
            _dao.delete(type)
        }
    }
}