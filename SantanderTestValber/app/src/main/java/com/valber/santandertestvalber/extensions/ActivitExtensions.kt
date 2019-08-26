package com.valber.santandertestvalber.extensions

import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.valber.data.model.UserAccount
import com.valber.santandertestvalber.ui.LoginActivity
import com.valber.santandertestvalber.ui.StatementActivity

fun <T> AppCompatActivity.navigater(activity: Class<T>) {
    startActivity(Intent(this, activity))
}
fun LoginActivity.navigaterWithValue(data: UserAccount) {
    var intent = Intent(this, StatementActivity::class.java)
    intent.putExtra(StatementActivity::class.java.simpleName, data)
    startActivity(intent)
    finish()
}