package com.accenture.android.app.testeandroid;

import com.accenture.android.app.testeandroid.helpers.FormatHelper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Denis Magno on 10/07/2020.
 * denis_magno16@hotmail.com
 */
public class FormatHelperUnitTest {
    @Test
    public void formatarReal_isCorrect() {
        assertEquals("R$ 0,00", FormatHelper.formatarReal(0.0));
        assertEquals("R$ 1,00", FormatHelper.formatarReal(1.0));
        assertEquals("-R$ 1,00", FormatHelper.formatarReal(-1.0));
        assertEquals("R$ 10,00", FormatHelper.formatarReal(10.0));
        assertEquals("-R$ 10,00", FormatHelper.formatarReal(-10.0));
        assertEquals("R$ 100,00", FormatHelper.formatarReal(100.0));
        assertEquals("-R$ 100,00", FormatHelper.formatarReal(-100.0));
        assertEquals("R$ 1.000,00", FormatHelper.formatarReal(1000.0));
        assertEquals("-R$ 1.000,00", FormatHelper.formatarReal(-1000.0));
    }

    @Test
    public void formatarAgenciaBanco_isCorrect() {
        assertEquals("01.234567-8", FormatHelper.formatarAgenciaBanco("012345678"));
        assertEquals("00.234567-8", FormatHelper.formatarAgenciaBanco("002345678"));
        assertEquals("00.034567-8", FormatHelper.formatarAgenciaBanco("000345678"));
        assertEquals("00.004567-8", FormatHelper.formatarAgenciaBanco("000045678"));
        assertEquals("00.000567-8", FormatHelper.formatarAgenciaBanco("000005678"));
        assertEquals("00.000067-8", FormatHelper.formatarAgenciaBanco("000000678"));
        assertEquals("00.000007-8", FormatHelper.formatarAgenciaBanco("000000078"));
        assertEquals("00.000000-8", FormatHelper.formatarAgenciaBanco("000000008"));
        assertEquals("00.000000-0", FormatHelper.formatarAgenciaBanco("000000000"));
        assertEquals("01.234567-8", FormatHelper.formatarAgenciaBanco("12345678"));
        assertEquals("00.234567-8", FormatHelper.formatarAgenciaBanco("2345678"));
        assertEquals("00.034567-8", FormatHelper.formatarAgenciaBanco("345678"));
        assertEquals("00.004567-8", FormatHelper.formatarAgenciaBanco("45678"));
        assertEquals("00.000567-8", FormatHelper.formatarAgenciaBanco("5678"));
        assertEquals("00.000067-8", FormatHelper.formatarAgenciaBanco("678"));
        assertEquals("00.000007-8", FormatHelper.formatarAgenciaBanco("78"));
        assertEquals("00.000000-8", FormatHelper.formatarAgenciaBanco("8"));
        assertEquals("00.000000-0", FormatHelper.formatarAgenciaBanco(""));
    }
}
