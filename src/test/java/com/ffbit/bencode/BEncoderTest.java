package com.ffbit.bencode;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BEncoderTest {
    private BEncoder encoder;
    private OutputStream out;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        encoder = new BEncoder(out);
    }

    @Test
    public void itShouldEncodeString() throws Exception {
        encoder.encode("foo");

        assertThat(out.toString(), is("3:foo"));
    }

    @Test
    public void itShouldEncodeInteger() throws Exception {
        encoder.encode(42);

        assertThat(out.toString(), is("i42e"));
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

        assertThat(out.toString(), is("l1:1i2ed3:keyli1e1:2eee"));
    }

}
