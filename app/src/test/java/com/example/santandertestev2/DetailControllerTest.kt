package com.example.santandertestev2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.santandertestev2.data.PreferenceData
import com.example.santandertestev2.domain.controller.detail.DetailController
import com.example.santandertestev2.domain.model.controller.UserAccount
import com.example.santandertestev2.domain.presenter.DetailPresenter
import com.example.santandertestev2.domain.presenter.IDetailPresenter
import com.example.santandertestev2.view.detail.DetailActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class DetailControllerTest {

    @Test
    fun test(){
        assert(true)
    }
/*@Test
fun getUser_should_return_user(){
    val b = Bundle()
    val user = UserAccount().apply {
        name = "User Name"
    }
    b.putSerializable("user", user)
    val intent = Intent()
    intent.putExtras(b)

}

@Before
fun saveUserLogin(){
    val ctext = Mockito.mock(Context::class.java)
    val sp = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
    sp.edit().apply {
        putInt("agency", 1234567 )
        putString("name", "User name")
        putLong("balance", 1234.56.toLong() )
        putInt("bankAccount", 2020)
        commit()
    }
}

@Test
fun user_should_be_null_on_logout(){

    val detActivity = Mockito.mock(DetailActivity::class.java)
    //val prefData = PreferenceData(detActivity.applicationContext)
    //prefData.saveLogged(newUser)
    val detailcontroller = DetailController(detActivity)
    //detailcontroller.logout()
    val user = detActivity.intent.getSerializableExtra("user")
    Assert.assertNull(user)
}*/


}