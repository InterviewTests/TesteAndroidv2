package br.com.santanderteste.presenter;

import android.os.Bundle;

import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import br.com.santanderteste.model.LoginResponse;
import br.com.santanderteste.model.User;
import br.com.santanderteste.ui.interfaces.ILoginView;
import br.com.santanderteste.ui.interfaces.IUserRepository;
import br.com.santanderteste.utils.Const;
import br.com.santanderteste.utils.Cpf;
import br.com.santanderteste.utils.Utils;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class LoginPresenter {

    private ILoginView view;
    private IUserRepository userRepository;
    private Scheduler processScheduler;
    private Scheduler androidScheduler;

    public LoginPresenter(ILoginView view, IUserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;
    }

    /**
     * For testing
     *
     * @param view
     * @param userRepository
     */
    public LoginPresenter(ILoginView view, IUserRepository userRepository,
                          Scheduler processScheduler, Scheduler androidScheduler) {
        this.view = view;
        this.userRepository = userRepository;
        this.processScheduler = processScheduler;
        this.androidScheduler = androidScheduler;
    }

    /**
     * @param username
     * @param password
     */
    public void checkLoginData(String username, String password) {

        if (!username.isEmpty()
                && !password.isEmpty()
                && Utils.isValidPassword(password)
                && (Cpf.isValid(username) || Utils.isValidEmail(username))) {

            requestUserData(username, password);

        } else {
            view.showErrorMessage(Const.ERROR_MESSAGE);
        }
    }

    /**
     * Either loads the user data from DB or makes a new request to retrieve it
     */
    public void loadUserData() {

        Single<User> userObservable = userRepository.getUserFromDB();

        userObservable
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(User user) {
                        if (user != null) {

                            Bundle bundle = new Bundle();
                            bundle.putParcelable(Const.USER_DATA, user);

                            if (view.isViewAdde()) {
                                view.callStatementsFragment(bundle);
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });

    }

    /**
     * Insert a new user into the database
     *
     * @param user
     */
    private void insertNewUser(final User user) {

        Completable.fromAction(new Action() {
            @Override
            public void run() {
                userRepository.insertUser(user);
            }
        }).subscribeOn(Schedulers.io()).subscribe();

    }

    /**
     * Request user data from the api
     *
     * @param u
     * @param p
     */
    public void requestUserData(String u, String p) {

        Observable<LoginResponse> loginResponseObservable = userRepository.getUser(u, p);
        loginResponseObservable
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showProgress();
                        view.disableEditText();
                        view.hideErrorMessage();
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {

                        if (loginResponse.getError().getCode() != 0) {
                            view.hideProgress();
                            view.enableEditText();
                            view.showErrorMessage(loginResponse.getError().getMessage());
                        } else {

                            if (view.isViewAdde()) {

                                Bundle bundle = new Bundle();
                                bundle.putParcelable(Const.USER_DATA, loginResponse.getUser());
                                view.callStatementsFragment(bundle);

                                //save user data
                                insertNewUser(loginResponse.getUser());
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * get encrypted data
     *
     * @param data
     * @return
     * @throws Exception
     */
    public byte[] encryptUserData(String data) throws Exception {

        byte[] b = data.getBytes();

        //encryption key
        byte[] keyStart = data.getBytes();

        KeyGenerator kgen = KeyGenerator.getInstance(Const.AES);
        SecureRandom sr = SecureRandom.getInstance(Const.SECURE_RANDOM);
        sr.setSeed(keyStart);
        kgen.init(128, sr);
        SecretKey skey = kgen.generateKey();
        byte[] key = skey.getEncoded();

        //return encrypted data
        return Utils.encrypt(key, b);

        // decrypt
        //byte[] decryptedData = decrypt(key, encryptedData);
    }

}
