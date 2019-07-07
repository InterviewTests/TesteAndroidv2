package com.projeto.testedevandroidsantander.ui.mainScreen;

import android.view.View;
import android.widget.AdapterView;

import java.lang.ref.WeakReference;
import java.util.Calendar;

interface MainRouterInput {
}

public class MainRouter implements MainRouterInput, AdapterView.OnItemClickListener {

    public static String TAG = MainRouter.class.getSimpleName();
    public WeakReference<MainActivity> activity;
    private Calendar currentTime;


    public Calendar getCurrentTime() {
        if(currentTime == null) return Calendar.getInstance();
        return currentTime;
    }
    public void setCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
