package dev.ornelas.banckapp.domain.model

interface Mapper<in  A, out  B> {
    fun map (type: A) : B
}