package com.resource.bankapplication.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DateTypeDeserializerTest {

    @Test
    public void formatDate() {
        String date = DateTypeDeserializer.formatDate("2019-06-21");
        assertThat(date, is(equalTo("21/06/2019")));
    }
}