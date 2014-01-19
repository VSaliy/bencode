package com.ffbit.bencode.string;

import com.ffbit.bencode.Encoder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class StringEncoderTest {
    private Charset charset = Encoder.DEFAULT_CHARSET;

    private Encoder encoder;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        encoder = new StringEncoder(out, charset);
    }

    @Test
    public void itShouldBeApplicableForStrings() throws Exception {
        assertThat(encoder.isApplicable("a"), is(true));
    }

    @Test
    public void itShouldBeApplicableForNull() throws Exception {
        assertThat(encoder.isApplicable(null), is(false));
    }

    @Test
    @Parameters({
            "a, 1:a",
            "foo, 3:foo"
    })
    public void itShouldEncodeStrings(String input, String expectedOutput) throws Exception {
        encoder.encode(input);
        assertThat(out.toString(charset.name()), is(expectedOutput));
    }

}
