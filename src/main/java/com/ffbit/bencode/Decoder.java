package com.ffbit.bencode;

public interface Decoder<T> {

    T decode(String input);

}
