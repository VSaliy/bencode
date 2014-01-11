package com.ffbit.bencode;

public class IntegerEncoder implements Encoder {
    public static final String PREFIX = "i";
    public static final String SUFFIX = "e";

    @Override
    public String encode(int input) {
        return PREFIX + input + SUFFIX;
    }

}
