package br.com.rms.bankapp

import br.com.rms.bankapp.utils.extensions.formatYmdDateToDmyDateFormat
import org.junit.Test
import kotlin.test.assertTrue

class TestDateFormatter {

    @Test
    fun theDateIsValidIfConvertedFrom_YMD_to_DMY() {
        // Given
        val expectedDate = "01/12/2019"
        val serverDate = "2019-12-01"

        // when
        val result = expectedDate == serverDate.formatYmdDateToDmyDateFormat()

        // Then
        assertTrue { result }
    }

    @Test
    fun whenTheInputValueIsEmptyTheOutputMustBeEmpty() {
        // Given
        val expectedValue = ""
        val serverDate = ""

        // when
        val result = expectedValue == serverDate.formatYmdDateToDmyDateFormat()

        // Then
        assertTrue { result }
    }

}