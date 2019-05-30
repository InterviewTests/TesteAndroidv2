package monteoliva.testbank.controller;

import android.support.annotation.NonNull;

import java.util.List;

public interface OnRestStatementListener {
    void onSuccess(@NonNull List<StatementList> list);
    void onError();
}