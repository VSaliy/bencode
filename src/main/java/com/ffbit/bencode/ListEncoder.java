package com.ffbit.bencode;

import java.util.List;

public class ListEncoder implements Encoder<List> {
    private BEncoder parent;

    public ListEncoder(BEncoder parent) {
        this.parent = parent;
    }

    @Override
    public String encode(List input) {
        return "l" + parent.encode(input.toArray()) + "e";
    }

}
