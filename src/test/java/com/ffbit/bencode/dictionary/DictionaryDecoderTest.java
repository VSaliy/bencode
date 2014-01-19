package com.ffbit.bencode.dictionary;

import com.ffbit.bencode.BDecoder;
import com.ffbit.bencode.BDecoderException;
import com.ffbit.bencode.Decoder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class DictionaryDecoderTest {

    @Test
    public void itShouldDecodeEmptyDictionary() throws Exception {
        InputStream in = new ByteArrayInputStream("de".getBytes());
        DictionaryDecoder decoder = new DictionaryDecoder(in, new BDecoder(in));

        assertThat(decoder.decode(), is(Collections.<String, Object>emptyMap()));
    }

    @Test(expected = BDecoderException.class)
    @Parameters({
            "a"
    })
    public void itShouldThrowExceptionOnMalformedInput(String input) throws Exception {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Decoder decoder = new DictionaryDecoder(in, new BDecoder(in));

        decoder.decode();
    }

    @Test(expected = BDecoderException.class)
    public void itShouldNotAllowNonStringKeys() throws Exception {
        InputStream in = new ByteArrayInputStream("di1ei2ee".getBytes());
        Decoder decoder = new DictionaryDecoder(in, new BDecoder(in));

        decoder.decode();
    }

}
