package br.com.dpassos.bankandroid.login.data;

import android.util.Base64;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import br.com.dpassos.bankandroid.infra.CryptoLoader;
import br.com.dpassos.bankandroid.infra.ExceptionHandler;
import br.com.dpassos.bankandroid.infra.PersistenceContext;
import br.com.dpassos.bankandroid.infra.Requester;
import br.com.dpassos.bankandroid.login.business.Login;
import br.com.dpassos.bankandroid.login.business.UserAccount;

public class SimpleLoginRepository implements LoginRepository {

    private UserAccount currentAccount;

    private SimpleLoginRepository(){}

    public void clearAccount() {
        currentAccount = null;
    }

    public UserAccount getCurrentAccount() {
        return currentAccount;
    }

    @Override
    public UserAccount login(LoginRequest request) {
        Requester requester = new Requester();
        try {
            String response = requester.doPost("https://bank-app-test.herokuapp.com/api/login","user="+request.user
                    +"&password="+ request.password);
            Gson gson = new Gson();
            RequestModel requestModel = gson.fromJson(response, RequestModel.class);
            currentAccount = requestModel.userAccount;

            return currentAccount;

        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        return null;
    }

    @Override
    public Login currentLogin() {
        try {
            PersistenceContext persistenceContext = new PersistenceContext();
            FileInputStream fis = persistenceContext.openFileReader("login.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            byte[] bytes = (byte[])ois.readObject();
            bytes = Base64.decode(bytes, Base64.DEFAULT);
            bytes = CryptoLoader.getCrypto().decrypt(bytes);
            String json = new String(bytes);
            Login login = new Gson().fromJson(json, Login.class);
            ois.close();
            return login;
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        return null;
    }

    @Override
    public void saveLogin(Login login) {
        try {
            String json = new Gson().toJson(login);
            byte[] bytes = CryptoLoader.getCrypto().encrypt(json.getBytes());
            bytes = Base64.encode(bytes, Base64.DEFAULT);
            PersistenceContext persistenceContext = new PersistenceContext();
            FileOutputStream fos = persistenceContext.openFileWritter("login.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bytes);
            oos.close();
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }
}
