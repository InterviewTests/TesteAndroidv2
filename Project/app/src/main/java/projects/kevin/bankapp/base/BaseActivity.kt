package projects.kevin.bankapp.base

import androidx.appcompat.app.AppCompatActivity




abstract class BaseActivity : AppCompatActivity() {

    var active = false

    public override fun onStart() {
        super.onStart()
        active = true
    }

    public override fun onStop() {
        super.onStop()
        active = false
    }

    fun isActive(): Boolean {
        return active
    }
}