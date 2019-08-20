package com.example.mybank.utils.Exceptions

import java.lang.Exception

class PasswordInvalid (): Exception("Password deve ter no mínimo 6 caracteres, contendo um caractere especial e uma letra maiúsucla.")