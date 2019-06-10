package br.com.douglas.fukuhara.bank.home;

import android.content.DialogInterface;
import android.content.Intent;

import java.math.BigDecimal;
import java.util.List;

import br.com.douglas.fukuhara.bank.network.vo.StatementListVo;
import br.com.douglas.fukuhara.bank.network.vo.UserAccount;

public interface Contract {
    interface HomeActivityInput {
        void setHeaderInformation(String username, String account, String balance);
        void setRecentList(List<StatementListVo.StatementItem> statementList);
        void showContentLoader();
        void hideContentLoader();
        void showUserDataContainer();
        void hideUserDataContainer();
        void notifyResourceErrorToUser(int stringRes);
        void notifyErrorToUser(String message);
        void showNoDataAvailable();
        void hideNoDataAvailable();
    }

    interface HomeInteractorInput {
        void setHomeHeader(UserAccount userAccount);
        void fetchUserData();
        void disposeAll();
    }

    interface HomePresenterInput {
        void noUserInfo();
        void setHomeHeaderInfo(String name, String agency, String bankAccount, BigDecimal balance);
        void showUserDataErrorMessage(String message);
        void showUserDataGenericError();
        void setNoUserDataAvailable();
        void setUserDataInfo(List<StatementListVo.StatementItem> statementList);
        void onDataFetch();
    }

    interface HomeRouter {
        void onLogoutConfirmation(DialogInterface dialogInterface, int i);

    }
}
