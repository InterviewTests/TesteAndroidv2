package br.com.bankapp.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.bankapp.data.utils.convertStringToDate
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class UtilsDataTest {

    @Test
    fun convertStringToDateTest() {
        val dateString = "1914-06-02"
        val date = convertStringToDate(dateString)
        MatcherAssert.assertThat(date, CoreMatchers.instanceOf(Date::class.java))
        MatcherAssert.assertThat(date.toString(), CoreMatchers.equalTo("Tue Jun 02 00:00:00 BRT 1914"))
    }
}