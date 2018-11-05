package br.com.santanderteste.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import br.com.santanderteste.R;
import br.com.santanderteste.ui.BaseFragment;
import br.com.santanderteste.ui.activity.MainActivity;
import br.com.santanderteste.ui.interfaces.ILoginView;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class LoginFragment extends BaseFragment implements ILoginView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_layout, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideActionBar((MainActivity) getActivity());

    }

    @Override
    public void callStatementsFragment() {
        Navigation.findNavController(getActivity(), R.id.main_nav_host).navigate(R.id.StatementFragment);
    }
}
