package br.com.rcp.bank.injection.modules

import android.content.Context
import androidx.room.Room
import br.com.rcp.bank.annotations.Persistence
import br.com.rcp.bank.database.Database
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Module
class DatabaseModule {
	@Persistence
	@Provides
	fun provideDatabaseInstance(context: Context): Database {
		val	password	= SQLiteDatabase.getBytes("o_piloto_sumiu".toCharArray())
		val	factory 	= SupportFactory(password)
		return Room.databaseBuilder(context, Database::class.java, "database").fallbackToDestructiveMigration().openHelperFactory(factory).build()
	}
}