package com.ffbit.bencode;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BDecoderTest {
    private BDecoder decoder;

    @Before
    public void setUp() throws Exception {
        decoder = new BDecoder();
    }

    @Test
    public void itShouldDecodeStringAndInteger() throws Exception {
        assertThat(decoder.decode("3:fooi42e"), is(asList((Object) "foo", 42)));
    }

    @Test
    public void itShouldDecodeListOfStringAndInteger() throws Exception {
        assertThat(decoder.decode("l3:fooi42ee"), is(asList((Object) asList("foo", 42))));
    }

    @Test
    public void itShouldParseListOfIntegers() throws Exception {
        InputStream in = new ByteArrayInputStream("li1ei2ee".getBytes());
        decoder = new BDecoder(in);

        assertThat(decoder.decode(), is((Object)asList(1, 2)));
    }

}
