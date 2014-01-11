package com.ffbit.bencode;

public class IntegerDecoder implements Decoder {

    @Override
    public Integer decode(String input) {
        String number = input.substring(1, input.length() - 1);
        return Integer.valueOf(number);
    }

}
