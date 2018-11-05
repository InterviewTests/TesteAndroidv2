package br.com.santanderteste.presenter;

import br.com.santanderteste.model.User;
import br.com.santanderteste.repository.UserRepository;
import br.com.santanderteste.ui.interfaces.ILoginView;
import br.com.santanderteste.utils.Cpf;
import br.com.santanderteste.utils.Utils;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class LoginPresenter {

    private ILoginView view;
    private UserRepository userRepository;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.userRepository = new UserRepository();
    }

    /**
     *
     * @param username
     * @param password
     */
    public void checkLoginData(String username, String password){

        if (!username.isEmpty() && (Cpf.isValid(username) || Utils.isValidEmail(username))){

            User user = userRepository.getUser();



        } else {

        }

    }

}
