package com.ffbit.bencode.integer;

import com.ffbit.bencode.Encoder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class IntegerEncoderTest {

    @Test
    @Parameters({
            "0, i0e",
            "1, i1e",
            "-1, i-1e",
            "42, i42e"
    })
    public void itShouldEncodeIntegersOld(Integer input, String expectedOutput) throws Exception {
        Encoder encoder = new IntegerEncoder();

        assertThat(encoder.encode(input), is(expectedOutput));
    }

    @Test
    @Parameters({
            "0, i0e",
            "1, i1e",
            "-1, i-1e",
            "42, i42e"
    })
    public void itShouldEncodeIntegers(Integer input, String expectedOutput) throws Exception {
        OutputStream out = new ByteArrayOutputStream();
        Encoder encoder = new IntegerEncoder(out);

        encoder.encode(input);
        assertThat(out.toString(), is(expectedOutput));
    }

}
