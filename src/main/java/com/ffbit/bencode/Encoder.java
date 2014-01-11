package com.ffbit.bencode;

public interface Encoder<T> {

    String encode(T input);

}
