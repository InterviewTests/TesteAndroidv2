package teste.santander.com.santander;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Before
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void MainActivity_ShouldNOT_be_Null(){
        //Given
        MainActivity activity = new MainActivity();
        //When

        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void fetchMainMetaData() {
        //Given
        MainInteractorOutputSpy mainInteractorOutputSpy = new MainInteractorOutputSpy();
        MainActivity mainActivity = new MainActivity();
        mainActivity.output = mainInteractorOutputSpy;

        //When
        mainActivity.fetchMetaData();

        //Then
        Assert.assertTrue(mainInteractorOutputSpy.fetchMainMetaDataIsCalled);
    }

    private class MainInteractorOutputSpy implements MainInteractorInput {

        boolean fetchMainMetaDataIsCalled = false;
        MainRequest mainRequestCopy;

        @Override
        public void fetchMainMetaData(MainRequest request) {
            fetchMainMetaDataIsCalled = true;
            mainRequestCopy = request;
        }
    }

}