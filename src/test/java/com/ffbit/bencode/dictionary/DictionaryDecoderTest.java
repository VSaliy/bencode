package com.ffbit.bencode.dictionary;

import com.ffbit.bencode.BDecoder;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DictionaryDecoderTest {

    @Test
    public void itShouldDecodeEmptyDictionary() throws Exception {
        InputStream in = new ByteArrayInputStream("de".getBytes());
        DictionaryDecoder decoder = new DictionaryDecoder(in, new BDecoder(in));

        assertThat(decoder.decode(), is(Collections.<String, Object>emptyMap()));
    }

}
