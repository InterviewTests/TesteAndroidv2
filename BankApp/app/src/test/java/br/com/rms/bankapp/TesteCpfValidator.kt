package br.com.rms.bankapp

import br.com.rms.bankapp.utils.validations.user.CpfValidator
import org.junit.Test
import kotlin.test.assertTrue

class TesteCpfValidator{

    val cpfValidator = CpfValidator()

    @Test
    fun cpfValid(){
        assertTrue { cpfValidator.isCPF("40182994007") }
    }

    @Test
    fun cpfInvalid(){
        assertTrue { cpfValidator.isCPF("40182994000") }
    }

    @Test
    fun cpfIsEmpty(){
        assertTrue { cpfValidator.isCPF("") }
    }

}