package com.ffbit.bencode;

import java.util.List;

public class ListEncoder implements Encoder<List> {
    public static final String PREFIX = "l";
    public static final String SUFFFIX = "e";

    private BEncoder parent;

    public ListEncoder(BEncoder parent) {
        this.parent = parent;
    }

    @Override
    public String encode(List input) {
        return PREFIX + parent.encode(input.toArray()) + SUFFFIX;
    }

}
