package br.com.santanderteste.presenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import br.com.santanderteste.ui.interfaces.ILoginView;
import br.com.santanderteste.ui.interfaces.IUserRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private LoginPresenter loginPresenter;
    @Mock
    IUserRepository userRepository;
    @Mock
    ILoginView loginView;

    private String username;
    private String password;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        loginPresenter = new LoginPresenter(loginView, userRepository);

    }

    @Test
    public void shouldCall_RequestUserData() {

        username = "51544156049";
        password = "Test@1";

        loginPresenter.checkLoginData(username, password);

        verify(loginPresenter, times(1)).requestUserData(username, password);
    }

    @Test
    public void loadUserData() {
    }

    @Test
    public void requestUserData() {
    }
}