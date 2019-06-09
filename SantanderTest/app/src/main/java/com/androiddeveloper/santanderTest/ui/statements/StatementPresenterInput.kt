package com.androiddeveloper.santanderTest.ui.statements

import android.util.Log
import androidx.versionedparcelable.VersionedParcel
import com.androiddeveloper.santanderTest.data.model.statements.ItemDTO
import com.androiddeveloper.santanderTest.data.model.statements.Statements
import com.androiddeveloper.santanderTest.data.model.userdata.Data
import com.androiddeveloper.santanderTest.data.model.userdata.DataDTO
import java.lang.ref.WeakReference
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class StatementPresenterInput : IStatementContract.StatementPresenterInput {

    lateinit var bankInfo: WeakReference<IStatementContract.StatementInput>
    private val br = Locale("pt", "BR")

    override fun parseData(data: Data) {
        val newData = DataDTO()
        newData.bankAccount = data.bankAccount
        newData.name = data.name
        newData.agency = formatBankAccount(data.agency)
        newData.balance = formatBalance(data.balance)
        newData.userId = data.userId
        bankInfo.get()?.showData(newData)
    }

    override fun prepareBalance(statements: Statements) {
        val newArr = ArrayList<ItemDTO>()

        statements.statementList.forEach {
            val itemDTO = ItemDTO()
            itemDTO.value = formatBalance(it.value)
            itemDTO.title = it.title
            itemDTO.desc = it.desc
            itemDTO.date = formatDate(it.date)
            newArr.add(itemDTO)
        }

        bankInfo.get()?.showBalanceList(newArr)
    }

    fun formatDate(date: String): String {
        try {
            val input = SimpleDateFormat("yyyy-MM-dd", br)
            val output = SimpleDateFormat("dd/MM/yyyy", br)
            val date = input.parse(date)
            return output.format(date).toString()
        } catch (e: ParseException) {
            Log.e("Error", e.message)
            return ""
        }
    }

    fun formatBankAccount(agency: String): String {
        try {
            val first = agency.substring(0, 2)
            val second = agency.substring(2, agency.length - 1)
            val third = agency.substring(agency.length - 1)

            val stringBuilder = StringBuilder().apply {
                this.append(first)
                this.append(".")
                this.append(second)
                this.append("-")
                this.append(third)
            }
            return stringBuilder.toString()
        } catch (e: IndexOutOfBoundsException) {
            e.message
            return ""
        }
    }

    fun formatBalance(value: Double): String {
        val currency = Currency.getInstance(br).symbol
        val newValue = Formatter()
        newValue.format("%.2f", value)
        if (value > 0) {
            return "$currency $newValue"
        } else {
            val negativeValue = newValue.toString().replace("-","")
            return "- $currency $negativeValue"
        }

    }
}