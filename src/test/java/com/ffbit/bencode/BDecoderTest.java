package com.ffbit.bencode;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
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
    public void itShouldDecodeListOfStringAndIntegerOld() throws Exception {
        assertThat(decoder.decode("l3:fooi42ee"), is(asList((Object) asList("foo", 42))));
    }

    @Test
    public void itShouldParseListOfIntegers() throws Exception {
        InputStream in = new ByteArrayInputStream("li1ei2ee".getBytes());
        decoder = new BDecoder(in);

        assertThat(decoder.decode(), is((Object) asList(1, 2)));
    }

    @Test
    public void itShouldDecodeListOfStringAndInteger() throws Exception {
        InputStream in = new ByteArrayInputStream("li1e1:ae".getBytes());
        decoder = new BDecoder(in);

        assertThat(decoder.decode(), is((Object) asList(1, "a")));
    }

    @Test
    public void itShouldDecodeListOfStringAndIntegerAndNestedList() throws Exception {
        InputStream in = new ByteArrayInputStream("li1e1:ali2eee".getBytes());
        decoder = new BDecoder(in);

        assertThat(decoder.decode(), is((Object) asList(1, "a", asList(2))));
    }

    @Test
    public void itShouldDecodeDictionaryOfSingleIntegerValue() throws Exception {
        InputStream in = new ByteArrayInputStream("d1:ai1ee".getBytes());
        decoder = new BDecoder(in);

        assertThat(decoder.decode(), is((Object) singletonMap("a", 1)));
    }

    @Test
    public void itShouldDecodeDictionaryOfMultipleIntegerValues() throws Exception {
        InputStream in = new ByteArrayInputStream("d1:ai2e3:fooi42ee".getBytes());
        decoder = new BDecoder(in);
        HashMap<String, Object> expected = new HashMap<String, Object>() {{
            put("a", 2);
            put("foo", 42);
        }};

        assertThat(decoder.decode(), is((Object) expected));
    }

    @Test
    public void itShouldDecodeDictionaryOfMultipleIntegerValuesAndNestedList() throws Exception {
        InputStream in = new ByteArrayInputStream("d1:ai2e3:fooi42e1:lli1eee".getBytes());
        decoder = new BDecoder(in);
        HashMap<String, Object> expected = new HashMap<String, Object>() {{
            put("a", 2);
            put("foo", 42);
            put("l", asList(1));
        }};

        assertThat(decoder.decode(), is((Object) expected));
    }

}
