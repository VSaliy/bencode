package com.ffbit.bencode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DictionaryEncoder implements Encoder<Map<String, ?>> {
    public static final String PREFIX = "d";
    public static final String SUFFIX = "e";

    private final BEncoder parent;

    public DictionaryEncoder(BEncoder parent) {
        this.parent = parent;
    }

    @Override
    public String encode(Map<String, ?> input) {
        StringBuilder output = new StringBuilder(PREFIX);

        for (String key : getSortedKeys(input)) {
            output.append(parent.encode(key));
            output.append(parent.encode(input.get(key)));
        }

        output.append(SUFFIX);

        return output.toString();
    }

    private List<String> getSortedKeys(Map<String, ?> input) {
        List<String> keys = new ArrayList<String>(input.keySet());
        Collections.sort(keys);

        return keys;
    }

}
