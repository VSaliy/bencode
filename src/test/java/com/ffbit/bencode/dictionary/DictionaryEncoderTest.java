package com.ffbit.bencode.dictionary;

import com.ffbit.bencode.BEncoder;
import com.ffbit.bencode.Decoder;
import com.ffbit.bencode.Encoder;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DictionaryEncoderTest {
    private Encoder encoder;
    private ByteArrayOutputStream out;
    private Map<String, ? super Object> dictionary;
    private Charset charset;
    private String charsetName;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        charset = Decoder.DEFAULT_CHARSET;
        charsetName = charset.name();
        encoder = new DictionaryEncoder(new BEncoder(out, charset), out);
    }

    @Test
    public void itShouldEncodeEmptyDictionary() throws Exception {
        encoder.encode(emptyMap());

        assertThat(out.toString(charsetName), is("de"));
    }

    @Test
    public void itShouldEncodeDictionaryOfStringAndInteger() throws Exception {
        dictionary = Collections.<String, Object>singletonMap("answer", 42);

        encoder.encode(dictionary);

        assertThat(out.toString(charsetName), is("d6:answeri42ee"));
    }

    @Test
    public void itShouldEncodeDictionarySortedByKeysAlphabetically() throws Exception {
        dictionary = new LinkedHashMap<String, Object>();
        dictionary.put("z", "end");
        dictionary.put("a", "beginning");

        encoder.encode(dictionary);

        assertThat(out.toString(charsetName), is("d1:a9:beginning1:z3:ende"));
    }

}
