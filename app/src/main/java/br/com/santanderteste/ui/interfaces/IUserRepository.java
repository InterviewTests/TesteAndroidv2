package br.com.santanderteste.ui.interfaces;

import br.com.santanderteste.model.LoginResponse;
import br.com.santanderteste.model.StatementResponse;
import br.com.santanderteste.model.User;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IUserRepository {

    Observable<LoginResponse> getUser(String user, String password);

    Observable<StatementResponse> getStatements();

    Single<User> getUserFromDB();

    Long insertUser(User user);

    void deleteAll();
}
