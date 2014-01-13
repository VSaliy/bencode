package com.ffbit.bencode;

import java.io.IOException;

public interface Decoder<T> {

    @Deprecated
    T decode(String input);

    T decode() throws IOException;

}
