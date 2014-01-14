package com.ffbit.bencode.string;

import com.ffbit.bencode.Decoder;
import com.ffbit.bencode.Encoder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class StringEncoderTest {
    private Charset charset = Decoder.DEFAULT_CHARSET;

    private Encoder encoder;

    @Before
    public void setUp() throws Exception {
        encoder = new StringEncoder();
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
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Encoder encoder = new StringEncoder(out, charset);

        encoder.encode(input);
        assertThat(out.toString(charset.name()), is(expectedOutput));
    }

}
