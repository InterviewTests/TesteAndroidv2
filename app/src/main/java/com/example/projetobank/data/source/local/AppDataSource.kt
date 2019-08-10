package com.example.projetobank.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.projetobank.data.model.Usuario


@Database(
        entities = [
        Usuario::class],
        version = 1,
        exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object {

        private var INSTANCE: AppDataBase? = null

        private val lock = Any()

        fun getInstance(context: Context): AppDataBase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDataBase::class.java,
                            "bank")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}