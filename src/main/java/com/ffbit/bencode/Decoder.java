package com.ffbit.bencode;

import java.io.IOException;

public interface Decoder<T> {

    T decode() throws IOException;

}
