package br.com.dpassos.bankandroid.infra;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PersistenceContext {

    private static Context context;

    public static void setContext(Context context) {
        PersistenceContext.context = context;
    }

    public FileInputStream openFileReader(String fileName) throws FileNotFoundException {
        return context.openFileInput(fileName);
    }

    public FileOutputStream openFileWritter(String fileName) throws FileNotFoundException {
        return context.openFileOutput(fileName, Context.MODE_PRIVATE);
    }
}
