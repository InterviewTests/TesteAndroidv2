package br.com.santanderteste.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.santanderteste.model.User;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();

}
