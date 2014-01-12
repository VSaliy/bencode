package com.ffbit.bencode;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class StringDecoderTest {

    @Test
    @Parameters({
            "1:a, a",
            "3:foo, foo",
            "3:fooi5e, foo"
    })
    public void itShouldDecodeStringsOld(String input, String expectedOutput) throws Exception {
        Decoder decoder = new StringDecoder();

        assertThat(decoder.decode(input), is((Object) expectedOutput));
    }

    @Test
    @Parameters({
            "1:a, a",
            "3:foo, foo",
            "3:fooi5e, foo"
    })
    public void itShouldDecodeStrings(String input, String expected) throws Exception {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        StringDecoder decoder = new StringDecoder(in);

        assertThat(decoder.decode(), is(expected));
    }

}
