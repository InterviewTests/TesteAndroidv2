package teste.claudio.com.testsantander;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

public class SenhaTest {
    @Test
    public void Senha() {
        boolean Status = Util.ValidarSenha("cj161062");
        assertEquals(true, Util.ValidarSenha("Cj161062*_"));
    }
}
