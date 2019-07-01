package io.github.maikotrindade.bankapp.login.model

class FieldError(val errorMessage: Int, val fieldError: Field)

enum class Field { LOGIN, PASSWORD }