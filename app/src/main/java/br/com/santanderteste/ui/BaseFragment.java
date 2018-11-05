package br.com.santanderteste.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class BaseFragment extends Fragment {

    public void showActionBar(AppCompatActivity activity) {
        activity.getSupportActionBar().hide();
    }

    public void hideActionBar(AppCompatActivity activity) {
        activity.getSupportActionBar().hide();
    }

}
