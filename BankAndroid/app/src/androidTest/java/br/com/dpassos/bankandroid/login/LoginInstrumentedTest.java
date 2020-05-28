package br.com.dpassos.bankandroid.login;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import br.com.dpassos.bankandroid.infra.PersistenceContext;
import br.com.dpassos.bankandroid.login.business.Login;
import br.com.dpassos.bankandroid.login.data.Factory;
import br.com.dpassos.bankandroid.login.data.LoginRepository;

@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

    @Before
    public void config() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PersistenceContext.setContext(appContext);
    }

    @Test
    public void loginUserPersistTest() {
        LoginRepository loginRepository = new Factory().getRepositoryImpl();
        Login login = loginRepository.currentLogin();

        if(login == null) {
            login = new Login();
            login.user = "User";
            login.password = "Passs";
            loginRepository.saveLogin(login);
            login = loginRepository.currentLogin();
        }

        Assert.assertNotNull(login);
        Assert.assertEquals(login.user, "User");
        Assert.assertEquals(login.password, "Passs");
    }
}
