package com.ffbit.bencode;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class IntegerDecoderTest {

    @Test
    @Parameters({
            "i0e, 0",
            "i1e, 1",
            "i-1e, -1",
            "i42e, 42"
    })
    public void itShouldEncodeIntegers(String input, int expectedOutput) throws Exception {
        Decoder decoder = new IntegerDecoder();

        assertThat(decoder.decode(input), is((Object) expectedOutput));
    }

}
