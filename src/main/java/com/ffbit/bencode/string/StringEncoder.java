package com.ffbit.bencode.string;

import com.ffbit.bencode.Encoder;

public class StringEncoder implements Encoder<String> {
    public static final String SEPARATOR = ":";

    @Override
    public String encode(String input) {
        String str = String.valueOf(input);
        return str.length() + SEPARATOR + str;
    }

}
