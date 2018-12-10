package com.accenture.primo.bankapp.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserTest {
    lateinit var objUser: User

    @Before
    fun config() {
        objUser = User(99,"Primo", "12.3456-7", "0001", 9876.54f)
    }

    @Test
    fun is_a_object_user_valid() {
        Assert.assertEquals(User::class.java, objUser::class.java)
        Assert.assertTrue( objUser.id > 0)
        Assert.assertTrue( objUser.bankAccount.length > 0)
        Assert.assertTrue( objUser.agency != "")
        Assert.assertEquals(9876.54f, objUser.balance)
    }
}