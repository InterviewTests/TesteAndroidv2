package ssilvalucas.testeandroidv2;

import org.junit.Assert;
import org.junit.Test;

import ssilvalucas.testeandroidv2.util.FormatUtil;

import static org.hamcrest.core.Is.is;

public class ScreenHomeTest {
    @Test
    public void testFormatDateShow() {
        /* Testa formato de exibição da data*/

        /**GIVEN**/
        String dataInput = "2014-05-09";

        /**WHEN**/
        String dataShow = FormatUtil.formatDate(dataInput);

        /**THEN**/
        Assert.assertThat(dataShow, is("09/05/2014"));
    }

    @Test
    public void testFormatValueShow() {
        /* Testa formato de exibição do valor*/

        /**GIVEN**/
        double valueInput = 30.123;

        /**WHEN**/
        String dataShow = FormatUtil.formatBalance(valueInput);

        /**THEN**/
        Assert.assertThat(dataShow, is("R$ 30,12"));
    }

    @Test
    public void testFormatBankAccountShow() {
        /* Testa formato de exibição da conta */

        /**GIVEN**/
        String agency = "2050";
        String bank = "011112224";

        /**WHEN**/
        String dataShow = FormatUtil.formatBankAccount(bank, agency);

        /**THEN**/
        Assert.assertThat(dataShow, is("2050 / 01.111222-4"));
    }
}
