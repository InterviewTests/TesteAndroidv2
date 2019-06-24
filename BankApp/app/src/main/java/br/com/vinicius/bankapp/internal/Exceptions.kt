package br.com.vinicius.bankapp.internal

class ValidationException(error:String): Exception(error)

class NotConnectionNetwork():Exception()