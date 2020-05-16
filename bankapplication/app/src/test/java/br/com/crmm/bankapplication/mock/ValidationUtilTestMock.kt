package br.com.crmm.bankapplication.mock

import br.com.crmm.bankapplication.util.ValidationUtil
import org.koin.core.KoinComponent
import org.koin.core.inject

object ValidationUtilTestMock: KoinComponent {

    const val invalidData = "AAA777"
    const val validCpf = "777.874.150-45"
    const val validEmail = "example@myemail.com.br"
    val validationUtils: ValidationUtil by inject()
}