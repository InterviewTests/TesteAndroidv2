package br.com.crmm.bankapplication.mock

import br.com.crmm.bankapplication.util.PasswordUtil
import org.koin.core.KoinComponent
import org.koin.core.inject

object PasswordUtilTestMock: KoinComponent {

    const val validPassword = "A1abcde@321"
    const val validPasswordLongPassword = "!@#$%¨&*()_+QWERTY<.,>:qwerty"
    const val invalidPasswordOnlyNumber = "124567"
    const val invalidPasswordOnlyLettersAllCaps = "AAAAAAAA"
    const val invalidPasswordOnlyLetters = "bbbbbbbb"
    const val invalidPasswordOnlySpecialCharacter = "!@#$%¨&¨&**())"
    const val invalidPasswordLongPassword = "!@#$%¨&*()_+QWERTY<.,>:"
    val passwordUtil: PasswordUtil by inject()

}