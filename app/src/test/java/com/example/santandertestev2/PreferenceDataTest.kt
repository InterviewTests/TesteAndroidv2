package com.example.santandertestev2

import android.content.Context
import android.content.SharedPreferences
import com.example.santandertestev2.data.PreferenceData
import com.example.santandertestev2.domain.model.UserAccount
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito

class PreferenceDataTest {

    private val sp = Mockito.mock(SharedPreferences::class.java)
    private val editor = Mockito.mock(SharedPreferences.Editor::class.java)
    val context = Mockito.mock(Context::class.java)
    private var user = UserAccount().apply {
        name = "My User Teste"
        bankAccount =2021
        agency = 1234567
        balance = 87368.72
    }


    @Before
    fun prepare(){
        Mockito.`when`(context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)).thenReturn(sp)
        Mockito.`when`(sp.edit()).thenReturn(editor)
        Mockito.`when`(editor.commit()).thenReturn(true)
        Mockito.`when`(editor.clear()).thenReturn(editor)
        Mockito.`when`(sp.getString("name",null)).thenReturn("Name user")
        Mockito.`when`(sp.getInt(eq("bankAccount"), anyInt())).thenReturn(2030)
        Mockito.`when`(sp.getLong(eq("balance"), anyLong())).thenReturn(76324582)
        Mockito.`when`(sp.getInt(eq("agency"), anyInt())).thenReturn(7654321)
    }

    @Test
    fun missing_property_name_when_save_user(){
        val prefData = PreferenceData(context)
        prefData.saveLogged(user)
        Mockito.verify(editor).putString(eq("name"), anyString())
    }

    @Test
    fun missing_property_agency_when_save_user(){
        val prefData = PreferenceData(context)
        prefData.saveLogged(user)
        Mockito.verify(editor).putInt(eq("agency"), anyInt())
    }

    @Test
    fun missing_property_balance_when_save_user(){
        val prefData = PreferenceData(context)
        prefData.saveLogged(user)
        Mockito.verify(editor).putLong(eq("balance"), anyLong())
    }

    @Test
    fun missing_property_bankAccount_when_save_user(){
        val prefData = PreferenceData(context)
        prefData.saveLogged(user)
        Mockito.verify(editor).putInt(eq("bankAccount"), anyInt())
    }

    @Test
    fun missing_method_apply_when_save_user(){
        val prefData = PreferenceData(context)
        prefData.saveLogged(user)
        Mockito.verify(editor).apply()
    }

    @Test
    fun preference_method_should_return_a_user(){
        val prefData = PreferenceData(context)
        val user = prefData.getUser()
        Assert.assertNotNull(user)
    }

    @Test
    fun missing_clear_or_apply_method_to_clear(){
        val prefData = PreferenceData(context)
        prefData.clearData()
        Mockito.verify(editor).clear()
        Mockito.verify(editor).apply()

    }

}