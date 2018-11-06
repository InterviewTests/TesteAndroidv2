package br.com.santanderteste.ui.interfaces;

import java.util.List;

import br.com.santanderteste.model.Statement;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public interface IStatementView {
    void updateStatementList(List<Statement> statements);

    void showErrorMessage(String message);

    void showProgress();

    void hideProgress();

    boolean isViewAdded();
}
