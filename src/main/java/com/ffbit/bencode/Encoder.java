package com.ffbit.bencode;

import java.io.IOException;

public interface Encoder<T> {
    char INTEGER_PREFIX = 'i';
    char LIST_PREFIX = 'l';
    char DICTIONARY_PREFIX = 'd';
    char END_SUFFIX = 'e';
    char STRING_SEPARATOR = ':';

    boolean isApplicable(Object value);

    void encode(T input) throws IOException;

}
