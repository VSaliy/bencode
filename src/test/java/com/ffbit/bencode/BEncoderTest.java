package com.ffbit.bencode;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BEncoderTest {
    private Charset charset = Decoder.DEFAULT_CHARSET;
    private String charsetName = charset.name();

    private BEncoder encoder;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        encoder = new BEncoder(out);
    }

    @Test
    public void itShouldEncodeString() throws Exception {
        encoder.encode("foo");

        assertThat(out.toString(charsetName), is("3:foo"));
    }

    @Test
    public void itShouldEncodeInteger() throws Exception {
        encoder.encode(42);

        assertThat(out.toString(charsetName), is("i42e"));
    }

    @Test(expected = BEncoderException.class)
    public void itShouldRejectNulls() throws Exception {
        encoder.encode(null);
    }

    @Test(expected = BEncoderException.class)
    public void itShouldRejectUnsupportedTypes() throws Exception {
        encoder.encode(new Object());
    }

    @Test
    public void itShouldEncodeComplexData() throws Exception {
        List<?> input = asList("1", 2, singletonMap("key", asList(1, "2")));

        encoder.encode(input);

        assertThat(out.toString(charsetName), is("l1:1i2ed3:keyli1e1:2eee"));
    }

}
