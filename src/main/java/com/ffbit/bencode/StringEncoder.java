package com.ffbit.bencode;

public class StringEncoder implements Encoder<String> {

    @Override
    public String encode(String input) {
        String str = String.valueOf(input);
        return str.length() + ":" + str;
    }

}
