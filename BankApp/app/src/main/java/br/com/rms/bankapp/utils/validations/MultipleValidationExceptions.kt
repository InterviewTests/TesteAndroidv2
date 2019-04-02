package br.com.rms.bankapp.utils.validations

class MultipleValidationExceptions(val validationExceptions: MutableList<ValidationException>) : ValidationException()
