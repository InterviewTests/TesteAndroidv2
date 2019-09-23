package com.example.santandertestev2.domain.Util

import java.text.NumberFormat
import java.util.*
import java.util.regex.Pattern

class AppUtil {

    companion object {

        fun validateCPF(cpf : String) : Boolean{
            val cpfClean = cpf.replace(".", "").replace("-", "")

            if (cpfClean.length != 11)
                return false

            try {
                val number  = cpfClean.toLong()
            }catch (e : Exception){
                return false
            }
            //continue
            var dvCurrent10 = cpfClean.substring(9,10).toInt()
            var dvCurrent11= cpfClean.substring(10,11).toInt()

            //the sum of the nine first digits determines the tenth digit
            val cpfNineFirst = IntArray(9)
            var i = 9
            while (i > 0 ) {
                cpfNineFirst[i-1] = cpfClean.substring(i-1, i).toInt()
                i--
            }
            //multiple the nine digits for your weights: 10,9..2
            var sumProductNine = IntArray(9)
            var weight = 10
            var position = 0
            while (weight >= 2){
                sumProductNine[position] = weight * cpfNineFirst[position]
                weight--
                position++
            }
            //Verify the nineth digit
            var dvForTenthDigit = sumProductNine.sum() % 11
            dvForTenthDigit = 11 - dvForTenthDigit //rule for tenth digit
            if(dvForTenthDigit > 9)
                dvForTenthDigit = 0
            if (dvForTenthDigit != dvCurrent10)
                return false

            //### verify tenth digit
            var cpfTenFirst = cpfNineFirst.copyOf(10)
            cpfTenFirst[9] = dvCurrent10
            //multiple the nine digits for your weights: 10,9..2
            var sumProductTen = IntArray(10)
            var w = 11
            var p = 0
            while (w >= 2){
                sumProductTen[p] = w * cpfTenFirst[p]
                w--
                p++
            }
            //Verify the nineth digit
            var dvForeleventhDigit = sumProductTen.sum() % 11
            dvForeleventhDigit = 11 - dvForeleventhDigit //rule for tenth digit
            if(dvForeleventhDigit > 9)
                dvForeleventhDigit = 0
            if (dvForeleventhDigit != dvCurrent11)
                return false

            return true
        }

        fun validateEmail(email:String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

        fun validatePassword(pass:String):Boolean{
            val passRegex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_@#%!*:;?$\\.,%]).{4,20})"
            val pattern = Pattern.compile(passRegex)
            val matcherPass = pattern.matcher(pass)
            return matcherPass.matches()
        }

        fun formatMoneyBr(number:Double, showSymbol : Boolean = false) :String{
            val numberFormat = NumberFormat.getCurrencyInstance(
                Locale("pt", "BR")
            )
            val symbol = numberFormat.currency.symbol
            if (!showSymbol){
                return numberFormat.format(number).replace(symbol, "")
            }else{
                return numberFormat.format(number)
            }
        }

        fun formatAgency(agency: String): String {
            var str = agency
            if (str.length > 7) {
                str = StringBuilder(str).insert(str.length - 1, "-")
                    .insert(str.length - 7, ".")
                    .toString()
            }
            return str
        }
    }
}