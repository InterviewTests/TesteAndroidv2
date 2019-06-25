package br.com.fernandodutra.testeandroidv2.activities;

import android.content.Context;
import android.os.AsyncTask;

import br.com.fernandodutra.testeandroidv2.api.ClientAPI;
import br.com.fernandodutra.testeandroidv2.api.InterfaceAPI;
import br.com.fernandodutra.testeandroidv2.dao.UserAccountDAO;
import br.com.fernandodutra.testeandroidv2.dao.UserAccountDAOLiter;
import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.models.UserAccountResponse;

import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 23/06/2019
 * Time: 15:23
 * TesteAndroidv2
 */
public class UserAccountModel implements UserAccountContract.Model {

    private UserAccountDAO<UserAccount> userAccountDAO;

    public UserAccountModel(Context context) {
        userAccountDAO = new UserAccountDAOLiter(context);
    }

    @Override
    public void getUserAccount(final OnFinished onFinishedListener, Login login) {
        InterfaceAPI apiService =
                ClientAPI.getClient().create(InterfaceAPI.class);

        Call<UserAccountResponse> call = apiService.getUserAccount(login);
        call.enqueue(new Callback<UserAccountResponse>() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                UserAccountResponse userAccountResponse = response.body();
                onFinishedListener.onFinished(userAccountResponse);
            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void insert(UserAccount userAccount) {
        new UserAccountModel.InsertUserAccountAsyncTask(userAccountDAO).execute(userAccount);
    }

    @Override
    public void update(UserAccount userAccount) {
        new UserAccountModel.UpdateUserAccountAsyncTask(userAccountDAO).execute(userAccount);
    }

    @Override
    public void delete(UserAccount userAccount) {
        new UserAccountModel.DeleteUserAccountAsyncTask(userAccountDAO).execute(userAccount);
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
