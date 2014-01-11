package com.ffbit.bencode;

public class StringDecoder implements Decoder {

    @Override
    public Object decode(String input) {
        int separatorIndex = input.indexOf(":");
        int length = Integer.valueOf(input.substring(0, separatorIndex));
        String value = input.substring(separatorIndex + 1, separatorIndex + 1 + length);

        return value;
    }

}
