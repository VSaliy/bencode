package com.ffbit.bencode;

public class IntegerEncoder implements Encoder {

    @Override
    public String encode(int input) {
        return "i" + input + "e";
    }

}
