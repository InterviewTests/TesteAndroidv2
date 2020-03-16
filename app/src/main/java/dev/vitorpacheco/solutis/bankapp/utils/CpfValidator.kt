package dev.vitorpacheco.solutis.bankapp.utils

object ValidacaoUtils {
    private val CPF_INVALIDOS: MutableSet<String> = HashSet()
    private const val EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    fun isValidCpf(numero: String?): Boolean {
        return if (numero == null || numero.length != 11) {
            false
        } else !CPF_INVALIDOS.contains(numero) && validateMod11(
            numero,
            0,
            8,
            9,
            false,
            1,
            9
        ) && validateMod11(numero, 0, 9, 10, false, 0, 9)
    }

    @JvmOverloads
    fun validateMod11(
        input: String,
        begin: Int,
        end: Int,
        verifierIndex: Int,
        x: Boolean = false,
        firstNumber: Int = begin,
        lastNumber: Int = end
    ): Boolean {
        var verifierIndexLocal = verifierIndex
        if (verifierIndexLocal < 0) {
            verifierIndexLocal += input.length
        }
        return try {
            mod11(input, begin, end, x, firstNumber, lastNumber) == input[verifierIndexLocal]
        } catch (e: NumberFormatException) {
            false
        }
    }

    @JvmOverloads
    fun mod11(
        input: String,
        begin: Int,
        end: Int,
        x: Boolean = false,
        firstNumber: Int = begin,
        lastNumber: Int = end
    ): Char {
        var increment = 1
        var incrementMultiply = 1

        if (end < begin) {
            increment = -1
        }

        if (lastNumber < firstNumber) {
            incrementMultiply = -1
        }

        var soma = 0
        var multiplyBy = firstNumber - incrementMultiply
        var i = begin - increment

        while (i != end) {
            i += increment
            multiplyBy += incrementMultiply

            val digit = Character.digit(input[i], 10)

            if (digit < 0) {
                throw NumberFormatException()
            }

            soma += digit * multiplyBy

            if (multiplyBy == lastNumber) {
                multiplyBy = firstNumber - incrementMultiply
            }
        }

        return when (val resto = soma % 11) {
            11 -> '0'
            10 -> if (x) 'X' else '0'
            else -> Character.forDigit(resto, 10)
        }
    }

    init {
        CPF_INVALIDOS.add("00000000000")
        CPF_INVALIDOS.add("11111111111")
        CPF_INVALIDOS.add("22222222222")
        CPF_INVALIDOS.add("33333333333")
        CPF_INVALIDOS.add("44444444444")
        CPF_INVALIDOS.add("55555555555")
        CPF_INVALIDOS.add("66666666666")
        CPF_INVALIDOS.add("77777777777")
        CPF_INVALIDOS.add("88888888888")
        CPF_INVALIDOS.add("99999999999")
    }
}
