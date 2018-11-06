package br.com.santanderteste.repository;

import br.com.santanderteste.model.LoginResponse;
import br.com.santanderteste.model.StatementResponse;
import br.com.santanderteste.model.User;
import br.com.santanderteste.network.APIClient;
import br.com.santanderteste.network.networkinterface.IRetrofitClient;
import br.com.santanderteste.repository.db.UserDatabase;
import br.com.santanderteste.ui.interfaces.IUserRepository;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * @author JhonnyBarbosa
 */
public class UserRepository implements IUserRepository {

    private IRetrofitClient retrofitClient;
    private UserDatabase userDatabase;

    public UserRepository(UserDatabase userDatabase) {
        this.retrofitClient = APIClient.getClient().create(IRetrofitClient.class);
        this.userDatabase = userDatabase;
    }

    @Override
    public Observable<LoginResponse> getUser(String user, String password) {

        return retrofitClient.getUser(user, password);

    }

    @Override
    public Observable<StatementResponse> getStatements() {
        return retrofitClient.getStatements();
    }

    @Override
    public Single<User> getUserFromDB() {
        return userDatabase.daoAccess().fetchUser();
    }

    @Override
    public Long insertUser(User user) {
        return userDatabase.daoAccess().insertUser(user);
    }

    @Override
    public void deleteAll() {
        userDatabase.daoAccess().deleteAll();
    }

}
