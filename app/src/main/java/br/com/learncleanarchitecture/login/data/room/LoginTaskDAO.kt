package br.com.learncleanarchitecture.login.data.room

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import br.com.learncleanarchitecture.BuildConfig
import br.com.learncleanarchitecture.login.data.MapperLogin
import br.com.learncleanarchitecture.login.presentation.Login
import br.com.learncleanarchitecture.util.CryptoFakeUtil
import br.com.learncleanarchitecture.util.DataResponse
import br.com.learncleanarchitecture.util.OnResult


class LoginTaskDAO {

    companion object {
        private var db: CleanCodeRoomDatabase? = null

        fun getInstanceRoom(context: Context): CleanCodeRoomDatabase {

            if (db == null) {
                db = CleanCodeRoomDatabase.getDatabase(context = context)
            }

            return db!!
        }

        fun saveLogin(
            loginCrudDao: LoginCrudDAO,
            loginEntity: LoginEntity,
            asyncDelegate: OnResult<Long>?
        ): Boolean {
            return saveOutOfMainThread(loginCrudDao, loginEntity)
        }

        private fun saveOutOfMainThread2(
            loginCrudDao: LoginCrudDAO,
            loginEntity: LoginEntity,
            asyncDelegate: OnResult<Long>?
        ) {
            SaveAsyncTask(loginCrudDao, loginEntity, asyncDelegate)
        }

        fun saveOutOfMainThread(
            loginCrudDao: LoginCrudDAO,
            loginEntity: LoginEntity
        ): Boolean {

            AsyncTask.execute {
                loginCrudDao.insert(loginEntity)
            }

            return true
        }

        fun checkHaveUser(loginCrudDao: LoginCrudDAO, dataResponse: DataResponse<Login>) {
            checkHaveUserOutOfMainThread(loginCrudDao, dataResponse)
        }

        private fun checkHaveUserOutOfMainThread(loginCrudDao: LoginCrudDAO, dataResponse: DataResponse<Login>) {
            try {
                CheckHaveUserAsyncTask(loginCrudDao, dataResponse).execute()
            } catch (e: Exception) {
                Log.e(LoginTaskDAO.javaClass.simpleName, e.localizedMessage)
            }
        }

        //region AsyncTask
        //region AsyncTask DeletePhrasesAsyncTask
        private class CheckHaveUserAsyncTask internal constructor(
            private val asyncPhraseDao: LoginCrudDAO,
            private val asyncDelegate: DataResponse<Login>?
        ) : AsyncTask<Void, Void, LoginEntity?>() {
            override fun doInBackground(vararg params: Void?): LoginEntity? {
                return asyncPhraseDao.getLastLogin()
            }

            override fun onPostExecute(result: LoginEntity?) {
                asyncDelegate?.let {
                    result?.let {

                        result.pass = CryptoFakeUtil.decode(result.pass!!)

                        val login: Login? = MapperLogin.loginEntityToLogin(result)
                        login?.let {
                            asyncDelegate.onSuccess(it)
                        } ?: run {
                            if (noUserLogged()) if (asyncDelegate != null) {
                                return asyncDelegate.onSuccess(Login())
                            }
                        }
                    }
                }?:run {
                    if (noUserLogged()) if (asyncDelegate != null) {
                        return asyncDelegate.onSuccess(Login())
                    }
                }
            }

            private fun noUserLogged(): Boolean {
                if (asyncDelegate != null) {
                    return true
                }
                return false
            }
        }
        //endregion AsyncTask DeletePhrasesAsyncTask

        private class SaveAsyncTask internal constructor(
                        private val loginCrudDao: LoginCrudDAO,
                        private val loginEntity: LoginEntity,
                        private val asyncDelegate: OnResult<Long>?) : AsyncTask<Void, Void, Long>() {
            override fun doInBackground(vararg args: Void): Long? {
                loginCrudDao.insert(loginEntity)
                return 1L
            }

            override fun onPostExecute(result: Long?) {
                asyncDelegate?.let {
                    result?.let {
                        asyncDelegate.onSuccess(result)
                    }
                }
            }
        }
        //endregion AsyncTask
    }
}