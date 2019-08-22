package teste.santander.com.santander;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MainInteractorTest {


    @Test
    public void isValidPassword() {

        //Given
        String password = "Test@1";
        boolean result;
        MainInteractor mi = new MainInteractor();

        //When
         result = mi.isValidPassword(password);

        //Then
         assertTrue(result);

    }

}