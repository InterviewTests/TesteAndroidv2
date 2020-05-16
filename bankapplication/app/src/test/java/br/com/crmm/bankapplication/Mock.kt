package br.com.crmm.bankapplication

import br.com.crmm.bankapplication.util.CPFUtil
import br.com.crmm.bankapplication.util.ValidationUtil

object Mock {

    val invalidData = "AAA777"

    val validCpf = "777.874.150-45"

    val validEmail = "example@myemail.com.br"

    val validationUtils = ValidationUtil(CPFUtil())
}