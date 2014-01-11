package com.ffbit.bencode;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BEncoderTest {
    private BEncoder encoder;

    @Before
    public void setUp() throws Exception {
        encoder = new BEncoder();
    }

    @Test
    public void itShouldEncodeStringAndInteger() throws Exception {
        assertThat(encoder.encode("foo", 42), is("3:fooi42e"));
    }

    @Test(expected = BEncoderException.class)
    public void itShouldRejectNulls() throws Exception {
        encoder.encode(null, 1);
    }

    @Test
    public void itShouldEncodeComplexData() throws Exception {
        List<?> input = asList("1", 2, singletonMap("key", asList(1, "2")));

        assertThat(encoder.encode(input.toArray()), is("1:1i2ed3:keyli1e1:2ee"));
    }

    @Test(expected = BEncoderException.class)
    public void itShouldRejectUnsupportedTypes() throws Exception {
        encoder.encode(new Object());
    }

}
