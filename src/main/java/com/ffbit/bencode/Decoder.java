package com.ffbit.bencode;

import java.io.IOException;
import java.nio.charset.Charset;

public interface Decoder<T> {
    Charset DEFAULT_CHARSET = Charset.forName("US-ASCII");

    boolean isApplicable(int i);

    T decode() throws IOException;

}
