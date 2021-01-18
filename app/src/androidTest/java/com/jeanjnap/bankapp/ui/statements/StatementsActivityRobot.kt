package com.jeanjnap.bankapp.ui.statements

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jeanjnap.bankapp.R
import com.jeanjnap.bankapp.ui.login.LoginActivity
import com.jeanjnap.bankapp.util.extension.onView
import com.jeanjnap.bankapp.util.extension.verify
import com.jeanjnap.bankapp.util.extension.verifyText
import com.jeanjnap.bankapp.util.matcher.RecyclerViewMatcher.Companion.withItemCount
import com.jeanjnap.bankapp.util.matcher.RecyclerViewMatcher.Companion.withRecyclerViewItemIdAtPosition
import com.jeanjnap.bankapp.util.matcher.withDrawable
import com.jeanjnap.bankapp.util.matcher.withHeightSize
import com.jeanjnap.bankapp.util.matcher.withWidthSize

fun statementsActivityRobot(func: StatementsActivityRobot.() -> Unit) =
    StatementsActivityRobot().apply(func)

class StatementsActivityRobot {

    fun elementsMustBeConfiguredCorrectly() = apply {
        withId(R.id.tv_name).onView {
            verifyText(
                R.color.white,
                "Jose da Silva Teste",
                25,
                R.font.helvetica_neue_light
            )
        }
        withId(R.id.iv_logout).onView {
            verify(withWidthSize(28))
            verify(withHeightSize(28))
            verify(withDrawable(R.drawable.ic_logout))
        }
        withId(R.id.tv_account).onView {
            verifyText(
                R.color.white,
                "Conta",
                12,
                R.font.helvetica_neue
            )
        }
        withId(R.id.tv_account_number_value).onView {
            verifyText(
                R.color.white,
                "2050 / 01.111222-4",
                25,
                R.font.helvetica_neue_light
            )
        }
        withId(R.id.tv_balance).onView {
            verifyText(
                R.color.white,
                "Saldo",
                12,
                R.font.helvetica_neue
            )
        }
        withId(R.id.tv_balance_value).onView {
            verifyText(
                R.color.white,
                "R$ 10,00",
                25,
                R.font.helvetica_neue_light
            )
        }
        withId(R.id.tv_recent).onView {
            verifyText(
                R.color.river_bed,
                "Recentes",
                16,
                R.font.helvetica_neue
            )
        }
        withId(R.id.rv_statements).onView {
            verify(withItemCount(5))
        }
        withRecyclerViewItemIdAtPosition(R.id.rv_statements, 0, R.id.tv_title).onView {
            verifyText(
                R.color.cadet_blue,
                "Pagamento",
                16,
                R.font.helvetica_neue
            )
        }
        withRecyclerViewItemIdAtPosition(R.id.rv_statements, 0, R.id.tv_date).onView {
            verifyText(
                R.color.cadet_blue,
                "17/01/2021",
                12,
                R.font.helvetica_neue
            )
        }
        withRecyclerViewItemIdAtPosition(R.id.rv_statements, 0, R.id.tv_desc).onView {
            verifyText(
                R.color.river_bed,
                "Conta de luz",
                16,
                R.font.helvetica_neue
            )
        }
        withRecyclerViewItemIdAtPosition(R.id.rv_statements, 0, R.id.tv_value).onView {
            verifyText(
                R.color.river_bed,
                "R$ 10,00",
                20,
                R.font.helvetica_neue_light
            )
        }
    }

    fun logout() {
        withId(R.id.iv_logout).onView {
            perform(click())
        }
    }

    fun verifyLoginScreen() {
        intended(hasComponent(LoginActivity::class.java.name))
    }
}