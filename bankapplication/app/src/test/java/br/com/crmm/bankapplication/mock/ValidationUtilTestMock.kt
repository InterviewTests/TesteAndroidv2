package br.com.crmm.bankapplication.mock

import br.com.crmm.bankapplication.util.CPFUtil
import br.com.crmm.bankapplication.util.ValidationUtil

object ValidationUtilTestMock {

    const val invalidData = "AAA777"
    const val validCpf = "777.874.150-45"
    const val validEmail = "example@myemail.com.br"
    val validationUtils = ValidationUtil(CPFUtil())
}