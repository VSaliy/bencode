package com.ffbit.bencode.integer;

import com.ffbit.bencode.Encoder;
import com.ffbit.bencode.integer.IntegerEncoder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void itShouldEncodeIntegers(int input, String expectedOutput) throws Exception {
        Encoder encoder = new IntegerEncoder();

        assertThat(encoder.encode(input), is(expectedOutput));
    }

}
