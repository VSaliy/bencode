package com.ffbit.bencode;

public interface Encoder<T> {

    boolean isApplicable(Object value);

    void encode(T input);

}
