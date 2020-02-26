package com.br.myapplication.login

import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.myapplication.helper.AppHelper
import com.br.myapplication.helper.SharedPreferencesManager
import com.br.myapplication.model.ResponseLogin
import com.br.myapplication.model.UserAccount
import com.br.myapplication.service.ApiResult
import com.br.myapplication.usercase.LoginUserCase
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginViewModel(private val loginUserCase: LoginUserCase,
                     private val sharedPreferencesManager: SharedPreferencesManager) : ViewModel() {

    val liveDataResponse: MutableLiveData<ApiResult<ResponseLogin>> = MutableLiveData()

    fun doLogin(user: String, password: String)  {
        loginUserCase(LoginUserCase.Params(user, password)) {
            liveDataResponse.value = it
        }
    }

    fun getUser() : UserAccount? {
        val userString = sharedPreferencesManager.retrieveUser()
        return userString?.let {
            return AppHelper.convertStringToObj(it, UserAccount::class.java)
        }
    }

    fun saveUserPreferences(userAccount: UserAccount) {
        sharedPreferencesManager.saveUser(AppHelper.convertObjToString(userAccount))
    }

    fun isValidateUserField(user: String) : Boolean = checkCpf(user) || checkValidEmail(user)

    fun isValidPasswordField(password: String) : Boolean {
        val pattern: Pattern = Pattern.compile(REGEX_PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    internal  fun checkValidEmail(et: String) = PatternsCompat.EMAIL_ADDRESS.matcher(et).matches()
    internal fun checkCpf(cpf: String): Boolean{
        val cpfClean = cpf.replace(".", "").replace("-", "")

        //## check if size is eleven
        if (cpfClean.length != 11)
            return false

        //## check if is number
        try {
            val number  = cpfClean.toLong()
        }catch (e : Exception){
            return false
        }

        //continue
        val dvCurrent10 = cpfClean.substring(9,10).toInt()
        val dvCurrent11= cpfClean.substring(10,11).toInt()

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
        val cpfTenFirst = cpfNineFirst.copyOf(10)
        cpfTenFirst[9] = dvCurrent10
        //multiple the nine digits for your weights: 10,9..2
        val sumProductTen = IntArray(10)
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

    companion object {
        const val REGEX_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    }
}