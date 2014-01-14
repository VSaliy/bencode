package com.ffbit.bencode;

public interface Encoder<T> {

    boolean isApplicable(Object value);

    String encode(T input);

}
