package br.com.rphmelo.bankapp.common.extensions

fun String.isEmail(): Boolean {
    val pattern = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()

    return matches(pattern)
}

fun String.isValidPassword(): Boolean {
    val pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*\\W+).*\$".toRegex()
    return matches(pattern)
}

fun String.formatAgency() = "${this.substring(0,2)}.${this.substring(2,8)}-${this.substring(8,9)}"

fun String.isValidCPF(): Boolean {

    if (this.isEmpty()) return false

    val numbers = arrayListOf<Int>()

    this.filter { it.isDigit() }.forEach {
        numbers.add(it.toString().toInt())
    }

    if (numbers.size != 11) return false

    (0..9).forEach { n ->
        val digits = arrayListOf<Int>()
        (0..10).forEach { digits.add(n) }
        if (numbers == digits) return false
    }

    val dv1 = ((0..8).sumBy { (it + 1) * numbers[it] }).rem(11).let {
        if (it >= 10) 0 else it
    }

    val dv2 = ((0..8).sumBy { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
        if (it >= 10) 0 else it
    }

    return numbers[9] == dv1 && numbers[10] == dv2
}