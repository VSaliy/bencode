package com.ffbit.bencode;

import java.io.IOException;

public interface Encoder<T> {

    boolean isApplicable(Object value);

    void encode(T input) throws IOException;

}
