package com.ffbit.bencode;

import static com.ffbit.bencode.IntegerEncoder.PREFIX;
import static com.ffbit.bencode.IntegerEncoder.SUFFIX;

public class IntegerDecoder implements Decoder<Integer> {

    @Override
    public Integer decode(String input) {
        String number = input.substring(PREFIX.length(), input.length() - SUFFIX.length());
        return Integer.valueOf(number);
    }

}
