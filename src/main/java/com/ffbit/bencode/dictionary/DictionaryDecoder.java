package com.ffbit.bencode.dictionary;

import com.ffbit.bencode.BDecoder;
import com.ffbit.bencode.Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.ffbit.bencode.Encoder.DICTIONARY_PREFIX;
import static com.ffbit.bencode.Encoder.END_SUFFIX;

public class DictionaryDecoder implements Decoder<Map<String, Object>> {
    private final InputStream in;
    private final BDecoder parent;
    private char current;

    public DictionaryDecoder(InputStream in, BDecoder bDecoder) {
        this.in = in;
        this.parent = bDecoder;
    }

    public boolean isApplicable(int b) {
        return b == DICTIONARY_PREFIX;
    }

    @Override
    public Map<String, Object> decode() throws IOException {
        checkPrefix();
        Map<String, Object> content = readContent();
        checkSuffix();
        return content;

    }

    private void checkPrefix() throws IOException {
        if (read() != DICTIONARY_PREFIX) {
            throw new DictionaryDecoderException(
                    "Unexpected beginning of dictionary <" + current + ">, " +
                            "expected <" + DICTIONARY_PREFIX + ">");
        }
    }

    private char read() throws IOException {
        current = (char) in.read();
        return current;
    }

    private Map<String, Object> readContent() throws IOException {
        Map<String, Object> content = new HashMap<String, Object>();

        in.mark(1);
        while (read() != END_SUFFIX && current != EOF) {
            in.reset();

            String key = (String) parent.decode();
            Object value = parent.decode();
            content.put(key, value);

            in.mark(1);
        }
        in.reset();

        return content;
    }

    private void checkSuffix() throws IOException {
        if (read() != END_SUFFIX) {
            throw new DictionaryDecoderException(
                    "Unexpected beginning of dictionary <" + current + ">, " +
                            "expected <" + END_SUFFIX + ">");
        }
    }

}
