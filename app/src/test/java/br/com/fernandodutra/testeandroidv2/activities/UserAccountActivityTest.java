package br.com.fernandodutra.testeandroidv2.activities;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 23/06/2019
 * Time: 17:20
 * TesteAndroidv2
 */
public class UserAccountActivityTest implements UserAccountContract.View {

    public int responseIntView = -1;
    public String responseStringView = "";
    public boolean navigate = false;

    @Override
    public void showMessage(int response) {
        responseIntView = response;
    }

    @Override
    public void showMessage(String response) {
        responseStringView = response;
    }

    @Override
    public void navigate(Integer userId) {
        navigate = true;
    }
}
