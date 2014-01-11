package com.ffbit.bencode;

import static com.ffbit.bencode.StringEncoder.SEPARATOR;

public class StringDecoder implements Decoder<String> {

    @Override
    public String decode(String input) {
        int separatorIndex = input.indexOf(SEPARATOR);
        int length = Integer.valueOf(input.substring(0, separatorIndex));
        String value = input.substring(separatorIndex + 1, separatorIndex + 1 + length);

        return value;
    }

}
