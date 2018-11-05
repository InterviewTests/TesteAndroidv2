package br.com.santanderteste.repository;

import br.com.santanderteste.model.User;
import br.com.santanderteste.ui.interfaces.IUserRepository;

/**
 * @author JhonnyBarbosa
 */
public class UserRepository implements IUserRepository {

    @Override
    public User getUser() {
        return new User("1", "Jose Silva Teste", "2050", "011112224", 1000);
    }
}
