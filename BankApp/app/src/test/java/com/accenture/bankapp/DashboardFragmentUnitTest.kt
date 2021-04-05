package com.accenture.bankapp

import android.os.Bundle
import android.os.Looper
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import com.accenture.bankapp.network.models.UserAccount
import com.accenture.bankapp.screens.dashboard.DashboardFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import java.text.MessageFormat
import java.text.NumberFormat
import java.util.*

@RunWith(RobolectricTestRunner::class)
class DashboardFragmentUnitTest {
    @Test
    fun mainActivity_shouldNot_beNull() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()

        Assert.assertNotNull(mainActivity)
    }

    @Test
    fun loginFragment_shouldNot_beNull() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val userAccount = UserAccount(123, "Test Test Test", "1234", "123456789", 1000f)
        val bundle = Bundle()
        val fragmentManager = mainActivity.supportFragmentManager
        val dashboardFragment = DashboardFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        bundle.putSerializable("userAccount", userAccount)
        dashboardFragment.arguments = bundle
        fragmentTransaction.add(R.id.layoutContainer, dashboardFragment)
        fragmentTransaction.commit()
        shadowOf(Looper.getMainLooper()).idle()

        Assert.assertNotNull(dashboardFragment)
    }

    @Test
    fun loginFragment_should_displayUserData() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val userAccount = UserAccount(123, "Test Test Test", "1234", "123456789", 1000f)
        val bundle = Bundle()
        val fragmentManager = mainActivity.supportFragmentManager
        val dashboardFragment = DashboardFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        bundle.putSerializable("userAccount", userAccount)
        dashboardFragment.arguments = bundle
        fragmentTransaction.add(R.id.layoutContainer, dashboardFragment)
        fragmentTransaction.commit()
        shadowOf(Looper.getMainLooper()).idle()

        val formattedAgency = MessageFormat("{1}{2}.{3}{4}{5}{6}{7}{8}-{9}").format(userAccount.agency.split("").toTypedArray())
        val testName = userAccount.name
        val testAccountAgency = String.format("%s / %s", userAccount.bankAccount, formattedAgency)
        val testBalance = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(userAccount.balance)

        val textNameText = dashboardFragment.textName.text
        val textAccountAgencyText = dashboardFragment.textAccountAgency.text
        val textBalanceText = dashboardFragment.textBalance.text

        Assert.assertEquals(textNameText, testName)
        Assert.assertEquals(textAccountAgencyText, testAccountAgency)
        Assert.assertEquals(textBalanceText, testBalance)
    }

    @Test
    fun loginFragment_should_displayDashboardMetadata() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val userAccount = UserAccount(123, "Test Test Test", "1234", "123456789", 1000f)
        val bundle = Bundle()
        val fragmentManager = mainActivity.supportFragmentManager
        val dashboardFragment = DashboardFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        bundle.putSerializable("userAccount", userAccount)
        dashboardFragment.arguments = bundle
        fragmentTransaction.add(R.id.layoutContainer, dashboardFragment)
        fragmentTransaction.commit()
        shadowOf(Looper.getMainLooper()).idle()

        runBlocking {
            withContext(Dispatchers.Default) {
                delay(5000L)
            }
        }
        shadowOf(Looper.getMainLooper()).idle()

        val listStatements = dashboardFragment.listStatements
        val recyclerStatements = dashboardFragment.recyclerStatements

        Assert.assertEquals(listStatements.size, recyclerStatements.adapter?.itemCount)
    }

    @Test
    fun textInfo_should_beEnable() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val userAccount = UserAccount(123, "Test Test Test", "1234", "123456789", 1000f)
        val bundle = Bundle()
        val fragmentManager = mainActivity.supportFragmentManager
        val dashboardFragment = DashboardFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        bundle.putSerializable("userAccount", userAccount)
        dashboardFragment.arguments = bundle
        fragmentTransaction.add(R.id.layoutContainer, dashboardFragment)
        fragmentTransaction.commit()
        shadowOf(Looper.getMainLooper()).idle()

        val textDashboardInfo = dashboardFragment.textDashboardInfo
        val recyclerStatements = dashboardFragment.recyclerStatements
        val testInfo = "Test info"

        dashboardFragment.enableInfo(testInfo)

        Assert.assertEquals(textDashboardInfo.text, testInfo)
        Assert.assertTrue(textDashboardInfo.isVisible)
        Assert.assertTrue(!recyclerStatements.isVisible)
    }

    @Test
    fun textInfo_should_beDisable() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val userAccount = UserAccount(123, "Test Test Test", "1234", "123456789", 1000f)
        val bundle = Bundle()
        val fragmentManager = mainActivity.supportFragmentManager
        val dashboardFragment = DashboardFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        bundle.putSerializable("userAccount", userAccount)
        dashboardFragment.arguments = bundle
        fragmentTransaction.add(R.id.layoutContainer, dashboardFragment)
        fragmentTransaction.commit()
        shadowOf(Looper.getMainLooper()).idle()

        val textDashboardInfo = dashboardFragment.textDashboardInfo
        val recyclerStatements = dashboardFragment.recyclerStatements
        val testInfo = "Test info"

        dashboardFragment.enableInfo(testInfo)
        dashboardFragment.disableInfo()

        Assert.assertEquals(textDashboardInfo.text, "")
        Assert.assertTrue(!textDashboardInfo.isVisible)
        Assert.assertTrue(recyclerStatements.isVisible)
    }

    @Test
    fun buttonLogout_should_performLogout() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val userAccount = UserAccount(123, "Test Test Test", "1234", "123456789", 1000f)
        val bundle = Bundle()
        val fragmentManager = mainActivity.supportFragmentManager
        val dashboardFragment = DashboardFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        bundle.putSerializable("userAccount", userAccount)
        dashboardFragment.arguments = bundle
        fragmentTransaction.add(R.id.layoutContainer, dashboardFragment)
        fragmentTransaction.commit()
        shadowOf(Looper.getMainLooper()).idle()

        val buttonLogout = dashboardFragment.buttonLogout

        buttonLogout.callOnClick()

        runBlocking {
            withContext(Dispatchers.Default) {
                delay(5000L)
            }
        }
        shadowOf(Looper.getMainLooper()).idle()

        Assert.assertNotNull(mainActivity.findViewById<LinearLayoutCompat>(R.id.layoutContainer).findViewById<Button>(R.id.buttonLogin))
    }
}