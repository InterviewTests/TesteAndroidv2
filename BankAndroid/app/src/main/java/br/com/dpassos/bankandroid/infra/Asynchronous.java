package br.com.dpassos.bankandroid.infra;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Asynchronous {

    private static ExecutorService pool = Executors.newSingleThreadExecutor();

    public void execute(final Runnable execution) {
        pool.execute(execution);
    }
}
