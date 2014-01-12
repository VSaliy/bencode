package com.ffbit.bencode;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

public class DictionaryDecoder {
    private final InputStream in;
    private final BDecoder parent;

    public DictionaryDecoder(InputStream in, BDecoder bDecoder) {
        this.in = in;
        this.parent = bDecoder;
    }

    public Map<String, Object> decode() {
        return Collections.emptyMap();
    }

}
