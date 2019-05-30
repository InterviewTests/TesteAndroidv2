package monteoliva.testbank.controller;

import android.support.annotation.NonNull;

public interface OnRestLoginListener {
    void onSuccess(@NonNull UserAccount userAccount);
    void onError();
}