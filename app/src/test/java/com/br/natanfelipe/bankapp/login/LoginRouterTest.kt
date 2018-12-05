package com.br.natanfelipe.bankapp.login

import android.content.Intent
import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.interactor.LoginInteractor
import com.br.natanfelipe.bankapp.router.LoginRouter
import com.br.natanfelipe.bankapp.view.home.HomeActivity
import com.br.natanfelipe.bankapp.view.login.LoginActivity
import com.br.natanfelipe.bankapp.view.login.LoginViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginRouterTest {

    lateinit var loginActivityMock: LoginActivity
    lateinit var api: RestApi
    lateinit var loginRouter: LoginRouter
    lateinit var intent: Intent


    @Before
    fun setUp() {
        loginRouter = LoginRouter()
        loginActivityMock = Mockito.mock(LoginActivity::class.java)
        api = RestApi()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun goToHomeActivity(){
        var loginViewModel = LoginViewModel()
        loginViewModel.userAccount.name = "Teste"
        loginViewModel.userAccount.agency = 1111
        loginViewModel.userAccount.bankAccount = "Any"
        loginViewModel.userAccount.balance = 2000.0
        loginViewModel.userAccount.userId = 1
        loginActivityMock.router = loginRouter
        intent = loginRouter.determineNextScreen(loginActivityMock)
        val targetActivityName = intent.component.className
        Assert.assertEquals(targetActivityName,HomeActivity::class.java)
    }

    /*@Test
    public void homeRouter_determineNextScreen_when_futureTripIs_Input() {
        //Given
        HomeRouter homeRouter = new HomeRouter();
        ArrayList<FlightViewModel> flightList = new ArrayList<>();
        FlightViewModel flight1 = new FlightViewModel();
        flight1.flightName = "9Z 231";
        flight1.startingTime = "2017/12/31";
        flight1.departureCity = "BLR";
        flight1.arrivalCity = "CJB";
        flight1.departureTime = "18:10";
        flight1.arrivalTime = "19:00";
        flightList.add(flight1);

        FlightViewModel flight2 = new FlightViewModel();
        flight2.flightName = "9Z 222";
        flight2.startingTime = "2016/12/31";
        flight2.departureCity = "BLR";
        flight2.arrivalCity = "CJB";
        flight2.departureTime = "18:10";
        flight2.arrivalTime = "19:00";
        flightList.add(flight2);

        HomeActivity homeActivity = Robolectric.setupActivity(HomeActivity.class);
        homeActivity.listOfVMFlights = flightList;
        homeActivity.router = homeRouter;
        homeRouter.activity = new WeakReference<HomeActivity>(homeActivity);

        Calendar currentTime = Calendar.getInstance();
        currentTime.set(2017,5,30,0,0,0);
        homeRouter.setCurrentTime(currentTime);


        //When - Futrure Trip is Input

        Intent intent = homeRouter.determineNextScreen(0);

        //Then
        String targetActivityName = intent.getComponent().getClassName();
        Assert.assertEquals("When the future travel date is passed to HomeRouter"
                +" Then next Intent should be BoardingActivity",targetActivityName, BoardingActivity.class.getName());
    }
*/

}