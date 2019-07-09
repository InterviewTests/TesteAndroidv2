package br.com.learncleanarchitecture

import android.app.Application
import br.com.learncleanarchitecture.login.data.room.CleanCodeRoomDatabase
import br.com.learncleanarchitecture.login.data.room.LoginTaskDAO
import com.squareup.leakcanary.LeakCanary

class AppController: Application() {
    override fun onCreate() {
        super.onCreate()

        db = LoginTaskDAO.getInstanceRoom(this)

        if(BuildConfig.memoryTest) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return
            }
            LeakCanary.install(this)
        }
    }

    companion object {
        var db: CleanCodeRoomDatabase? = null
    }

    fun getDb(): CleanCodeRoomDatabase {
        return db!!
    }

    fun setDb(database: CleanCodeRoomDatabase) {
        db = database
    }
}