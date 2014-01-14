package com.ffbit.bencode.integer;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class IntegerEncoderTest {
    private IntegerEncoder encoder;

    @Before
    public void setUp() throws Exception {
        encoder = new IntegerEncoder();
    }

    @Test
    public void itShouldBeApplicableForIntegers() throws Exception {
        assertThat(encoder.isApplicable(1), is(true));
    }

    @Test
    public void itShouldBeApplicableForNull() throws Exception {
        assertThat(encoder.isApplicable(null), is(false));
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
        encoder = new IntegerEncoder(out);

        encoder.encode(input);

        assertThat(out.toString(), is(expectedOutput));
    }

}
