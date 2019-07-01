package br.com.fernandodutra.testeandroidv2.activities.login;

import android.content.Context;
import android.os.AsyncTask;

import java.sql.SQLException;


import br.com.fernandodutra.testeandroidv2.dao.UserAccountDAO;
import br.com.fernandodutra.testeandroidv2.dao.UserAccountDAOLiter;

import br.com.fernandodutra.testeandroidv2.models.UserAccount;


/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 10:20
 * TesteAndroidv2_CleanCode
 */
interface LoginSQliteInput {
    void insert(UserAccount userAccount);

    void update(UserAccount userAccount);

    void delete(UserAccount userAccount);

    UserAccount findById(int userId);

    int nextID();
}

public class LoginSQliteWorker implements LoginSQliteInput {

    private Context context;
    private UserAccountDAO<UserAccount> userAccountDAO;

    public LoginSQliteWorker(Context context) {
        this.context = context;
        userAccountDAO = new UserAccountDAOLiter(context);
    }

    @Override
    public void insert(UserAccount userAccount) {
        new InsertUserAccountAsyncTask(userAccountDAO).execute(userAccount);
    }

    @Override
    public void update(UserAccount userAccount) {
        new UpdateUserAccountAsyncTask(userAccountDAO).execute(userAccount);
    }

    @Override
    public void delete(UserAccount userAccount) {
        new DeleteUserAccountAsyncTask(userAccountDAO).execute(userAccount);
    }

    @Override
    public UserAccount findById(int userId) {
        try {
            UserAccount byID = userAccountDAO.findByID(userId);
            return byID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int nextID() {
        try {
            int nextID = userAccountDAO.nextID();
            return nextID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static class InsertUserAccountAsyncTask extends AsyncTask<UserAccount, Void, Void> {
        private UserAccountDAO<UserAccount> userAccountDao;

        private InsertUserAccountAsyncTask(UserAccountDAO<UserAccount> userAccountDao) {
            this.userAccountDao = userAccountDao;
        }

        @Override
        protected Void doInBackground(UserAccount... userAccounts) {
            try {
                userAccountDao.insert(userAccounts[0]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class UpdateUserAccountAsyncTask extends AsyncTask<UserAccount, Void, Void> {
        private UserAccountDAO<UserAccount> userAccountDao;

        private UpdateUserAccountAsyncTask(UserAccountDAO<UserAccount> userAccountDao) {
            this.userAccountDao = userAccountDao;
        }

        @Override
        protected Void doInBackground(UserAccount... userAccounts) {
            try {
                userAccountDao.update(userAccounts[0]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class DeleteUserAccountAsyncTask extends AsyncTask<UserAccount, Void, Void> {
        private UserAccountDAO<UserAccount> userAccountDao;

        private DeleteUserAccountAsyncTask(UserAccountDAO<UserAccount> userAccountDao) {
            this.userAccountDao = userAccountDao;
        }

        @Override
        protected Void doInBackground(UserAccount... userAccounts) {
            try {
                userAccountDao.delete(userAccounts[0].getUserId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class DeleteAllUserAccountsAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserAccountDAO<UserAccount> userAccountDao;

        private DeleteAllUserAccountsAsyncTask(UserAccountDAO<UserAccount> userAccountDao) {
            this.userAccountDao = userAccountDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}
