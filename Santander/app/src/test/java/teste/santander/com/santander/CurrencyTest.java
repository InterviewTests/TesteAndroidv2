package teste.santander.com.santander;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import static org.junit.Assert.*;

public class CurrencyTest {

    @Before
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void CurrencyActivity_ShouldNOT_be_Null(){
        //Given
        Currency currencyActivity = new Currency();
        //When

        // Then
        Assert.assertNotNull(currencyActivity);
    }

    @Test
    public void fetchCurrencyMetaData() {
        //Given
        CurrencyInteractorOutputSpy currencyInteractorOutputSpy = new CurrencyInteractorOutputSpy();
        Currency currency = new Currency();
        currency.output = currencyInteractorOutputSpy;

        //When
        currency.fetchCurrencyMetaData();

        //Then
        Assert.assertTrue(currencyInteractorOutputSpy.fetchCurrencyMetaDataIsCalled);
    }

    private class CurrencyInteractorOutputSpy implements CurrencyInteractorInput {

        boolean fetchCurrencyMetaDataIsCalled = false;
        CurrencyRequest currencyRequestCopy;

        @Override
        public void fetchCurrencyMetaData(CurrencyRequest cr) {
            fetchCurrencyMetaDataIsCalled = true;
            currencyRequestCopy = cr;
        }
    }
}

