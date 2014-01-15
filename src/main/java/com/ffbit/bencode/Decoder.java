package com.ffbit.bencode;

import java.io.IOException;

public interface Decoder<T> {

    boolean isApplicable(int i);

    T decode() throws IOException;

}
