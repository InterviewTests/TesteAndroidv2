package br.com.santanderteste.presenter;

import br.com.santanderteste.model.StatementResponse;
import br.com.santanderteste.ui.interfaces.IStatementView;
import br.com.santanderteste.ui.interfaces.IUserRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class BankStatementsPresenter {

    private IStatementView view;
    private IUserRepository userRepository;

    public BankStatementsPresenter(IStatementView view, IUserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;
    }

    public void loadStatements() {

        Observable<StatementResponse> loginResponseObservable = userRepository.getStatements();


        loginResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StatementResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showProgress();
                    }

                    @Override
                    public void onNext(StatementResponse statementResponse) {

                        if (view.isViewAdded()) {
                            if (statementResponse.getError().getCode() != 0) {
                                view.showErrorMessage(statementResponse.getError().getMessage());
                            } else {

                                view.updateStatementList(statementResponse.getStatements());
                                view.hideProgress();
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
     * Deletes database data from the last logged user
     */
    public void logUserOut() {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                userRepository.deleteAll();
            }
        }).subscribeOn(Schedulers.io()).subscribe();

    }
}