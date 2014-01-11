package com.ffbit.bencode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BEncoderTest {

    @Test
    public void itShouldEncodeStringAndInteger() throws Exception {
        BEncoder encoder = new BEncoder();

        assertThat(encoder.encode("foo", 42), is("3:fooi42e"));
    }

}
