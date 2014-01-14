package com.ffbit.bencode.string;

import com.ffbit.bencode.Decoder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class StringDecoderTest {
    private Charset charset = Decoder.DEFAULT_CHARSET;

    private InputStream in;
    private StringDecoder decoder;

    @Test
    @Parameters({
            "1:a, a",
            "3:foo, foo",
            "3:fooi5e, foo"
    })
    public void itShouldDecodeStrings(String input, String expected) throws Exception {
        in = new ByteArrayInputStream(input.getBytes());
        decoder = new StringDecoder(in, charset);

        assertThat(decoder.decode(), is(expected));
    }

    @Test(expected = StringDecoderException.class)
    @Parameters({
            "1a",
            "0"
    })
    public void itShouldRejectMalformedInputs(String input) throws Exception {
        in = new ByteArrayInputStream(input.getBytes());
        decoder = new StringDecoder(in, charset);

        decoder.decode();
    }

}
