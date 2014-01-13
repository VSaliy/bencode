package com.ffbit.bencode.integer;

import com.ffbit.bencode.Decoder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class IntegerDecoderTest {
    private Decoder decoder;

    @Test(expected = IntegerDecoderException.class)
    @Parameters({
            "i1",
            "1e",
            "iAe",
            "i1d",
            "o1e"
    })
    public void itShouldRejectMalformedInput(String input) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        IntegerDecoder decoder = new IntegerDecoder(in);

        decoder.decode();
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
