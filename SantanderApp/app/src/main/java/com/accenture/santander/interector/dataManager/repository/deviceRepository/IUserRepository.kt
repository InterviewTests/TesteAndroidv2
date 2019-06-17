package com.accenture.santander.interector.dataManager.repository.deviceRepository

import com.accenture.santander.interector.dataManager.entity.UserEntity
import com.accenture.santander.interector.dataManager.repository.IRepository


/**
 * Created by dev on 13/05/2018.
 */
interface IUserRepository : IRepository<UserEntity> {
    fun findViewById(id: Int): UserEntity?
    fun findDesc(): UserEntity?
    fun find(): List<UserEntity>?
}