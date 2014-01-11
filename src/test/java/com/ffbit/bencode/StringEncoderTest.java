package com.ffbit.bencode;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class StringEncoderTest {

    @Test
    @Parameters({
            "a, 1:a",
            "foo, 3:foo"
    })
    public void itShouldEncodeStrings(String input, String expectedOutput) throws Exception {
        Encoder encoder = new StringEncoder();

        assertThat(encoder.encode(input), is(expectedOutput));
    }

}
