package com.ffbit.bencode.dictionary;

import com.ffbit.bencode.BEncoder;
import com.ffbit.bencode.BEncoderException;
import com.ffbit.bencode.Encoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DictionaryEncoder implements Encoder<Map<String, ?>> {
    private final BEncoder parent;
    private final Encoder<String> keyEncoder;
    private final OutputStream out;

    public DictionaryEncoder(BEncoder parent, Encoder<String> keyEncoder, OutputStream out) {
        this.parent = parent;
        this.keyEncoder = keyEncoder;
        this.out = out;
    }

    @Override
    public boolean isApplicable(Object value) {
        return value instanceof Map;
    }

    @Override
    public void encode(Map<String, ?> input) throws IOException {
        out.write(DICTIONARY_PREFIX);

        for (String key : getSortedKeys(input)) {
            encodeKey(key);
            encodeValue(input, key);
        }

        out.write(END_SUFFIX);
    }

    private void encodeKey(String key) throws IOException {
        if (keyEncoder.isApplicable(key)) {
            keyEncoder.encode(key);
        } else {
            throw new BEncoderException("Expected a string key, but was <" + key + ">");
        }
    }

    private void encodeValue(Map<String, ?> input, String key) throws IOException {
        parent.encode(input.get(key));
    }

    private List<String> getSortedKeys(Map<String, ?> input) {
        List<String> keys = new ArrayList<String>(input.keySet());
        Collections.sort(keys);

        return keys;
    }

}
