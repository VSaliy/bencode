package com.ffbit.bencode.dictionary;

import com.ffbit.bencode.BDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DictionaryDecoder {
    public static final char PREFIX = 'd';
    private static final char SUFFIX = 'e';
    private final InputStream in;
    private final BDecoder parent;
    private char current;

    public DictionaryDecoder(InputStream in, BDecoder bDecoder) {
        this.in = in;
        this.parent = bDecoder;
    }

    public boolean isApplicable(int b) {
        return b == PREFIX;
    }

    public Map<String, Object> decode() throws IOException {
        checkPrefix();
        Map<String, Object> content = readContent();
        checkSuffix();
        return content;

    }

    private void checkPrefix() throws IOException {
        if (read() != PREFIX) {
            throw new DictionaryDecoderException(
                    "Unexpected beginning of dictionary <" + current + ">, expected <" + PREFIX + ">");
        }
    }

    private char read() throws IOException {
        current = (char) in.read();
        return current;
    }

    private Map<String, Object> readContent() throws IOException {
        Map<String, Object> content = new HashMap<String, Object>();

        in.mark(1);
        while (read() != SUFFIX && current != -1) {
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
        if (read() != SUFFIX) {
            throw new DictionaryDecoderException(
                    "Unexpected beginning of dictionary <" + current + ">, expected <" + PREFIX + ">");
        }
    }

}
