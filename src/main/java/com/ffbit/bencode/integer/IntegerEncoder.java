package com.ffbit.bencode.integer;

import com.ffbit.bencode.Encoder;

public class IntegerEncoder implements Encoder<Integer> {
    public static final String PREFIX = "i";
    public static final String SUFFIX = "e";

    @Override
    public String encode(Integer input) {
        return PREFIX + input + SUFFIX;
    }

}