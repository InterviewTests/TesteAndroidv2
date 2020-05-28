package br.com.dpassos.bankandroid.login;

import org.junit.Assert;
import org.junit.Test;
import br.com.dpassos.bankandroid.infra.Requester;
import br.com.dpassos.bankandroid.login.business.LoginControl;
import br.com.dpassos.bankandroid.login.business.UserAccount;
import br.com.dpassos.bankandroid.login.data.Factory;
import br.com.dpassos.bankandroid.login.data.LoginRepository;
import br.com.dpassos.bankandroid.login.data.LoginRequest;

public class LoginUnitTest {

    @Test
    public void loginEndpointTest(){

        Requester requester = new Requester();
        String defaultResponse = "{\"userAccount\":{\"userId\":1,\"name\":\"Jose da Silva Teste\",\"bankAccount\":\"2050\",\"agency\":\"012314564\",\"balance\":3.3445},\"error\":{}}";
        String noErrorResponse = "\"error\":{}";

        String response = null;
        try {
            response = requester.doPost("https://bank-app-test.herokuapp.com/api/login","user=tyest_user" +"&password=Test@1");
        }catch (Exception e){}

        Assert.assertNotNull(response);
        Assert.assertEquals(defaultResponse, response);
        Assert.assertTrue(response.contains(noErrorResponse));

    }

    @Test
    public void loginRepositoryTest() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "tyest_user";
        loginRequest.password = "Test@1";

        LoginRepository loginRepository = new Factory().getRepositoryImpl();
        UserAccount userAccount = loginRepository.login(loginRequest);

        Assert.assertNotNull(userAccount);
    }

    @Test
    public void loginControlTest(){
        LoginControl loginControl = new LoginControl();
        UserAccount account;
        try {
            loginControl.login("077.333.15473", "Test@1");
            Assert.fail();
        }catch (LoginControl.LoginException e){
            Assert.assertEquals(LoginControl.LoginStatus.INVALID_USER, e.status);
        }

        try {
           loginControl.login("07733315473", "Test@1");
            Assert.fail();
        }catch (LoginControl.LoginException e){
            Assert.assertEquals(LoginControl.LoginStatus.INVALID_USER, e.status);
        }

        try {
            loginControl.login("077.333.154-73", "Test1");
            Assert.fail();
        }catch (LoginControl.LoginException e){
            Assert.assertEquals(LoginControl.LoginStatus.INVALID_PASS, e.status);
        }

        try {
            account = loginControl.login("077.333.154-73", "Test@1");
            Assert.assertNotNull(account);
        }catch (LoginControl.LoginException e){
            Assert.fail();
        }

        try {
            account = loginControl.login("teste@user.com", "Test@1");
            Assert.assertNotNull(account);
        }catch (LoginControl.LoginException e){
            Assert.fail();
        }
    }

}
