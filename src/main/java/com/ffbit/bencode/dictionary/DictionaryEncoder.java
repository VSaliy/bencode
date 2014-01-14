package com.ffbit.bencode.dictionary;

import com.ffbit.bencode.BEncoder;
import com.ffbit.bencode.Encoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DictionaryEncoder implements Encoder<Map<String, ?>> {
    public static final char PREFIX = 'd';
    public static final char SUFFIX = 'e';

    private final BEncoder parent;
    private final OutputStream out;

    public DictionaryEncoder(BEncoder parent, OutputStream out) {
        this.parent = parent;
        this.out = out;
    }

    @Override
    public boolean isApplicable(Object value) {
        return value instanceof Map;
    }

    @Override
    public String encode(Map<String, ?> input) {
        StringBuilder output = new StringBuilder(PREFIX);

        try {
            out.write(PREFIX);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : getSortedKeys(input)) {
            output.append(parent.encode(key));
            output.append(parent.encode(input.get(key)));
        }

        output.append(SUFFIX);

        try {
            out.write(SUFFIX);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    private List<String> getSortedKeys(Map<String, ?> input) {
        List<String> keys = new ArrayList<String>(input.keySet());
        Collections.sort(keys);

        return keys;
    }

}
