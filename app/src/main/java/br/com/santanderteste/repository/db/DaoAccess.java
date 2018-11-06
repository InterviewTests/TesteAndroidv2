package br.com.santanderteste.repository.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import br.com.santanderteste.model.User;
import io.reactivex.Single;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
@Dao
public interface DaoAccess {

    @Insert
    void inserSingleUser(User user);

    @Query("SELECT * FROM User")
    Single<User> fetchUser();

    @Insert
    Long insertUser(User user);

    @Query("DELETE FROM User")
    void deleteAll();
}
