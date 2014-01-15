package com.ffbit.bencode.list;

import com.ffbit.bencode.BDecoder;
import com.ffbit.bencode.Decoder;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListDecoderTest {

    @Test
    public void itShouldDecodeEmptyList() throws Exception {
        InputStream in = new ByteArrayInputStream("le".getBytes());
        Decoder decoder = new ListDecoder(in, new BDecoder(in));

        assertThat(decoder.decode(), is((Object) new ArrayList<Object>()));
    }

    @Test(expected = ListDecoderException.class)
    public void itShouldThrowExceptionOnMalformedInput() throws Exception {
        InputStream in = new ByteArrayInputStream("a".getBytes());
        Decoder decoder = new ListDecoder(in, new BDecoder(in));

        decoder.decode();
    }

}
