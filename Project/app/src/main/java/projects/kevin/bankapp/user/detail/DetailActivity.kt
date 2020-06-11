package projects.kevin.bankapp.user.detail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import projects.kevin.bankapp.R

class DetailActivity : AppCompatActivity() {

    companion object {
        fun startDetail(activity: Activity) {
            val start: Intent = Intent(activity, DetailActivity::class.java)
            activity.startActivity(start)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}
