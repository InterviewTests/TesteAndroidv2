package br.com.bankapp.helpers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.*


fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) { }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextInputLayout) return false
        val errorCharSequence = item.error ?: return false
        val error = errorCharSequence.toString()
        return expectedErrorText == error
    }
}

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView: RecyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        MatcherAssert.assertThat(adapter?.itemCount, CoreMatchers.`is`(expectedCount))
    }
}