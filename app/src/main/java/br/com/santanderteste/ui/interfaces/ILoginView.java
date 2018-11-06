package br.com.santanderteste.ui.interfaces;

import android.os.Bundle;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public interface ILoginView {

    void callStatementsFragment(Bundle bundle);

    void showProgress();

    void disableEditText();

    void enableEditText();

    void hideProgress();

    void showErrorMessage(String message);

    void hideErrorMessage();

    boolean isViewAdde();
}
