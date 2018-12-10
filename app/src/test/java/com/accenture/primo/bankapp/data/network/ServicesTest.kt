package com.accenture.primo.bankapp.data.network

import com.accenture.primo.bankapp.ui.login.LoginModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers

class ServicesTest() {
    private val user = "Primo"
    private val password = "1234"

    @Before
    fun config() {

    }

    @Test
    fun `Check login services`() {
        val testObserver = TestObserver<LoginModel.LoginResponse>()
        val objObservable = RemoteDataSource.getService().doLogin(user, password)

        objObservable
            //.subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
            .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }
}