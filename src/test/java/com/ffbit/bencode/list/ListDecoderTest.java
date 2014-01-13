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
    public void itShouldDecodeEmptyListOld() throws Exception {
        Decoder decoder = new ListDecoder(new BDecoder());

        assertThat(decoder.decode("le"), is((Object) new ArrayList<Object>()));
    }

    @Test
    public void itShouldDecodeEmptyList() throws Exception {
        InputStream in = new ByteArrayInputStream("le".getBytes());
        ListDecoder decoder = new ListDecoder(in, new BDecoder(in));

        assertThat(decoder.decode(), is((Object) new ArrayList<Object>()));
    }

}
