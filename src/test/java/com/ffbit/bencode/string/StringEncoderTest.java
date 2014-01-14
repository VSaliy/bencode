package com.ffbit.bencode.string;

import com.ffbit.bencode.Decoder;
import com.ffbit.bencode.Encoder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class StringEncoderTest {
    private Charset charset = Decoder.DEFAULT_CHARSET;

    @Test
    @Parameters({
            "a, 1:a",
            "foo, 3:foo"
    })
    public void itShouldEncodeStringsOld(String input, String expectedOutput) throws Exception {
        Encoder encoder = new StringEncoder();

        assertThat(encoder.encode(input), is(expectedOutput));
    }

    @Test
    @Parameters({
            "a, 1:a",
            "foo, 3:foo"
    })
    public void itShouldEncodeStrings(String input, String expectedOutput) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Encoder encoder = new StringEncoder(out, charset);

        encoder.encode(input);
        assertThat(out.toString(charset.name()), is(expectedOutput));
    }

}
