package com.ffbit.bencode;

public class StringEncoder implements Encoder {

    @Override
    public String encode(Object input) {
        String str = String.valueOf(input);
        return str.length() + ":" + str;
    }

}
