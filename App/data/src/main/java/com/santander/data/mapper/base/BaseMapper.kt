package com.santander.data.mapper.base

interface BaseMapper<Response, Entity> {
    fun toEntity(from: Response) : Entity
}