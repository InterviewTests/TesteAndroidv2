package teste.claudio.com.testsantander;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;


public class CPFTest {
    @Test
    public void CPF() {
        boolean CPFvalido = ValidaCPF.isCPF("72505044791");
        assertEquals(CPFvalido,ValidaCPF.isCPF("72505044791"));
    }
}
