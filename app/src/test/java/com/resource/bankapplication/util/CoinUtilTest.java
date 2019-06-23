package com.resource.bankapplication.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CoinUtilTest {

    @Test
    public void formatToCurrency(){

        String formatReal = CoinUtil.formatReal(200.0);

        assertThat(formatReal, is(equalTo("R$ 200,00")));
    }

}