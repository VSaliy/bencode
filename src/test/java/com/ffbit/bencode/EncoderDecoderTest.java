package com.ffbit.bencode;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EncoderDecoderTest {

    @Test
    public void itShouldDecodeEncoded() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BEncoder encoder = new BEncoder(out);
        encoder.encode(1);
        encoder.encode("foo");
        encoder.encode(asList("1", 42));
        encoder.encode(singletonMap("key", asList("foo", singletonMap("a", 42))));

        InputStream in = new ByteArrayInputStream(out.toByteArray());
        BDecoder decoder = new BDecoder(in);

        assertThat(decoder.decode(), is((Object) 1));
        assertThat(decoder.decode(), is((Object) "foo"));
        assertThat(decoder.decode(), is((Object) asList("1", 42)));
        assertThat(decoder.decode(),
                is((Object) singletonMap("key", asList("foo", singletonMap("a", 42)))));
    }

}
