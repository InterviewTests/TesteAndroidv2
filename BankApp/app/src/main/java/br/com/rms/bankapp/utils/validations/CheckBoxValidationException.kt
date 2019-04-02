package br.com.rms.bankapp.utils.validations

class CheckBoxValidationException(val checkBoxId: Int, val errorMessageId: Int) : ValidationException()
