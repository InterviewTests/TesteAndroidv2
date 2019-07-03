package resource.estagio.testesantander.login;

import android.content.Context;

import resource.estagio.testesantander.domain.User;

public class LoginContract {
    public interface View{

        void navigateToList(User user );

        Context getContext();

        Context getActivity();

        void enableButton(boolean b);

        void errorPassword(String s);

        void errorUsername(String s);

        void showProgress(final boolean show);


    }
    public interface Presenter{

        void login(String username, String password);

        boolean validUsername(String user);

        boolean validPassword(String password);

    }
}
