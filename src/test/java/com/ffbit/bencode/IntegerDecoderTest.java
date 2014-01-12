package com.ffbit.bencode;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class IntegerDecoderTest {
    private Decoder decoder;

    @Before
    public void setUp() throws Exception {
        decoder = new IntegerDecoder();
    }

    @Test
    @Parameters({
            "i0e, 0",
            "i1e, 1",
            "i-1e, -1",
            "i42e, 42",
            "i1e3:foo, 1"
    })
    public void itShouldEncodeIntegers(String input, int expectedOutput) throws Exception {
        assertThat(decoder.decode(input), is((Object) expectedOutput));
    }

    @Test(expected = IntegerDecoderException.class)
    @Parameters({
            "i1",
            "1e",
            "iAe",
            "i1d",
            "o1e"
    })
    public void itShouldRejectMalformedInput(String input) throws Exception {
        decoder.decode(input);
    }

    @Test
    @Parameters({
            "i0e, 0",
            "i1e, 1",
            "i-1e, -1",
            "i42e, 42",
            "i1e3:foo, 1"
    })
    public void itShouldDecodeIntegers(String input, int expected) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        IntegerDecoder decoder = new IntegerDecoder(in);

        assertThat(decoder.decode(), is(expected));
    }

}
