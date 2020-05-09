package br.com.raphael.everis.module

import android.content.Context
import android.content.SharedPreferences
import br.com.raphael.everis.App
import br.com.raphael.everis.di.module.AppModule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.mockito.Mockito

class AppModuleTest(app: App) : AppModule(app) {

    override fun provideContext() = mock<Context> {
        on { getSharedPreferences(any(), any()) } doReturn mock {  }
    }

    override fun provideSharedPreferences(): SharedPreferences = Mockito.mock(SharedPreferences::class.java)
}
