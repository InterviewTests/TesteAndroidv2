package com.renanferrari.testeandroidv2.application.ui.statements;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.renanferrari.testeandroidv2.application.common.providers.SchedulerProvider;
import com.renanferrari.testeandroidv2.application.common.utils.DateFormatter;
import com.renanferrari.testeandroidv2.application.common.utils.MoneyFormatter;
import com.renanferrari.testeandroidv2.application.ui.login.LoginViewModel;
import com.renanferrari.testeandroidv2.domain.interactors.GetStatements;
import com.renanferrari.testeandroidv2.domain.interactors.GetUser;
import com.renanferrari.testeandroidv2.domain.interactors.Logout;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class StatementsViewModel extends ViewModel {

  private static final String TAG = LoginViewModel.class.getSimpleName();

  private final GetStatements getStatements;
  private final GetUser getUser;
  private final Logout logout;
  private final MoneyFormatter moneyFormatter;
  private final DateFormatter dateFormatter;
  private final SchedulerProvider schedulerProvider;

  private final MutableLiveData<UserState> userStateLiveData = new MutableLiveData<>();
  private final MutableLiveData<StatementsState> statementsStateLiveData = new MutableLiveData<>();
  private final CompositeDisposable disposables = new CompositeDisposable();

  @Inject public StatementsViewModel(final GetStatements getStatements, final GetUser getUser,
      final Logout logout, final MoneyFormatter moneyFormatter, final DateFormatter dateFormatter,
      final SchedulerProvider schedulerProvider) {
    this.getStatements = getStatements;
    this.getUser = getUser;
    this.logout = logout;

    this.moneyFormatter = moneyFormatter;
    this.dateFormatter = dateFormatter;
    this.schedulerProvider = schedulerProvider;

    this.statementsStateLiveData.setValue(StatementsState.createDefault());
  }

  @Override protected void onCleared() {
    disposables.dispose();
    super.onCleared();
  }

  public void onUserRequested() {
    disposables.add(getUser.execute()
        .map(user -> UserState.builder()
            .name(user.getName())
            .bankAccount(user.getBankAccount())
            .balance(moneyFormatter.format(user.getBalance()))
            .build())
        .compose(schedulerProvider.applySchedulers())
        .subscribe(
            userStateLiveData::setValue,
            throwable -> Log.d(TAG, "Error retrieving User: " + throwable.getMessage())));
  }

  public void onStatementsRequested() {
    disposables.add(getStatements.execute()
        .flatMapObservable(Observable::fromIterable)
        .map(statement -> StatementItem.create(statement, moneyFormatter, dateFormatter))
        .toList()
        .map(StatementsState::create)
        .compose(schedulerProvider.applySchedulers())
        .doOnSubscribe(d -> statementsStateLiveData.setValue(
            statementsStateLiveData.getValue().withLoading(true)))
        .subscribe(
            statementsStateLiveData::setValue,
            throwable -> Log.d(TAG, "Error retrieving Statements: " + throwable.getMessage())));
  }

  public void onLogoutRequested() {
    disposables.add(logout.execute()
        .compose(schedulerProvider.applySchedulers())
        .subscribe(
            () -> userStateLiveData.setValue(null),
            throwable -> Log.d(TAG, "Error logging out: " + throwable.getMessage())));
  }

  public LiveData<UserState> getObservableUserState() {
    return userStateLiveData;
  }

  public LiveData<StatementsState> getObservableStatementsState() {
    return statementsStateLiveData;
  }
}