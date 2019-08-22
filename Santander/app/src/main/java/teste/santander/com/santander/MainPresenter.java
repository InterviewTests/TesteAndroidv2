package teste.santander.com.santander;

import java.lang.ref.WeakReference;

interface MainPresenterInput {
    void presentMainMetaData(MainResponse response);
}

public class MainPresenter implements MainPresenterInput {
    public WeakReference<MainActivityInput> output;

    @Override
    public void presentMainMetaData(MainResponse response) {

    }
}
