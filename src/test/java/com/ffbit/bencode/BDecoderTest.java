package com.ffbit.bencode;

import org.junit.Before;
import org.junit.Test;

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

}
