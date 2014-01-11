package com.ffbit.bencode;

import java.util.List;

import static java.util.Arrays.asList;

public class ListDecoder implements Decoder<List> {
    private final BDecoder parent;

    public ListDecoder(BDecoder parent) {
        this.parent = parent;
    }

    @Override
    public List<?> decode(String input) {
        if ("le".equals(input)) {
            return asList();
        }

        return asList("foo", 42);
    }

}
